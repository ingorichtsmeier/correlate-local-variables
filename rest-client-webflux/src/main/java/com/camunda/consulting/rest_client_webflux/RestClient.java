package com.camunda.consulting.rest_client_webflux;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RestClient {

  private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

  WebClient client = WebClient.create("http://localhost:8080");

  Boolean waitInLoggerFor5sec = true;
  
  ObjectMapper objectMapper = new ObjectMapper();

  @PostConstruct
  public void sendRequests() {
//    sendRequestsSequentially();

    String correlatedValue = sendRequestsParallel();
    
    try {
      Task task = getTaskExecutionId();
      LOG.info("Task execution response: {}", task.executionId());
      String valueInDatabase = getVariableVar1(task.executionId()).value();
      LOG.info("Variable value in task: {}", valueInDatabase);
      completeTask(task.id());
      assert correlatedValue.equals(valueInDatabase) : "database doesn't contain the correlated payload";
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void sendRequestsSequentially() {
    LOG.info("start sending messages in a row");

    for (int i = 0; i < 5; i++) {
      String response = publishMessage(i, waitInLoggerFor5sec).block();
      LOG.info("response: {}", response);
    }

    LOG.info("Messages send in a row");
  }

  /**
   * Taken from "Simultaneous Spring WebClient Calls"
   * <p>
   * <a href="https://www.baeldung.com/spring-webclient-simultaneous-calls">
   * https://www.baeldung.com/spring-webclient-simultaneous-calls</a>
   */
  public String sendRequestsParallel() {
    LOG.info("start sending requests in parallel");

    Flux<String> flux = sendMessagesParallel(List.of(15, 16, 17), waitInLoggerFor5sec);

    List<String> responses = flux.collectList().block();

//    responses.stream().forEach(response -> LOG.info("result: {}", response));
    List<String> results = responses.stream().filter(value -> value.startsWith("Ex") == false).collect(Collectors.toList());
    assert results.size() == 1 : "found more than one user task after message correlation";
    LOG.info("correlated variable value: {}", results.get(0));

    LOG.info("All messages send");
    return results.get(0);
  }

  Flux<String> sendMessagesParallel(List<Integer> messageValues, Boolean wait) {
    return Flux.fromIterable(messageValues).flatMap(value -> this.publishMessage(value, wait));
  }

  Mono<String> publishMessage(Integer value, Boolean wait) {
    LOG.info("publish message with value {}", value);
    RequestBodySpec request =
        client.post().uri(URI.create("http://localhost:8080/engine-rest/message"));
    request.contentType(MediaType.APPLICATION_JSON);

    request.bodyValue("""
        {
          \"messageName\":\"testMessage\",
          \"resultEnabled\":true,
          \"processVariablesToTriggeredScope\": {
            \"var1\": {
              \"value\": %d
            },
            \"wait\": {
              \"value\": %b
            }
          }
        }
        """.formatted(value, wait));
        /*
    request.bodyValue("""
        {
          \"messageName\":\"testMessage\",
          \"resultEnabled\":true,
          \"processVariables\": {
            \"var1\": {
              \"value\": %d
            },
            \"wait\": {
              \"value\": %b
            }
          }
        }
        """.formatted(value, wait));
         */
    Mono<String> response1 = request.exchangeToMono(response -> {
      if (response.statusCode().equals(HttpStatus.OK)) {
        LOG.info("Payload: {}", value);
        return Mono.just(""+ value);
//        return response.bodyToMono(String.class);
      } else if (response.statusCode().is4xxClientError()) {
        LOG.info("Error Payload: {}", value);
        return Mono.just("Error Response");
      } else if (response.statusCode().is5xxServerError()) {
        LOG.info("Exception Payload: {}", value);
        return Mono.just("Ex: " + value);
//        return response.bodyToMono(String.class);
      } else {
        return response.createException().flatMap(Mono::error);
      }
    });
    return response1;
  }

  Task getTaskExecutionId() throws JsonMappingException, JsonProcessingException {
    LOG.info("Query for taskId");
    RequestBodySpec requestBodySpec =
        client.post().uri(URI.create("http://localhost:8080/engine-rest/task"));
    requestBodySpec.contentType(MediaType.APPLICATION_JSON);
    requestBodySpec.bodyValue("""
        {
            "name": "Confirm value"
        }
        """);
    Mono<String> response1 = requestBodySpec.exchangeToMono(response -> {
      return response.bodyToMono(String.class);
    });
    String jsonResponse = response1.block();
    List<Task> taskList = objectMapper.readValue(jsonResponse, new TypeReference<List<Task>>() {});
    return taskList.get(0);
  }

  Variable getVariableVar1(String executionId)
      throws JsonMappingException, JsonProcessingException {
    LOG.info("Query for variable 'var1' from execution {}", executionId);
    RequestBodySpec request =
        client.post().uri(URI.create("http://localhost:8080/engine-rest/variable-instance"));
    request.contentType(MediaType.APPLICATION_JSON);
    request.bodyValue("""
        {
          "variableName": "var1",
          "executionIdIn": ["%s"]
        }
        """.formatted(executionId));
    Mono<String> response1 = request.exchangeToMono(response -> {
      return response.bodyToMono(String.class);
    });
    String jsonResponse = response1.block();
    List<Variable> variables =
        objectMapper.readValue(jsonResponse, new TypeReference<List<Variable>>() {});
    return variables.get(0);
  }

  void completeTask(String taskId) {
    LOG.info("Complete task");
    RequestBodySpec requestBodySpec = client.post()
        .uri(URI.create("http://localhost:8080/engine-rest/task/" + taskId + "/complete"));
    requestBodySpec.contentType(MediaType.APPLICATION_JSON);
    requestBodySpec.bodyValue("{}");
    Mono<String> response1 = requestBodySpec.exchangeToMono(response -> {
      return response.bodyToMono(String.class);
    });
    response1.block();
  }
  
}

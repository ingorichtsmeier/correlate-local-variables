package com.camunda.consulting.rest_client_webflux;

import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RestClient {

  private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

  WebClient client = WebClient.create("http://localhost:8080");

  Boolean waitInLoggerFor5sec = true;

  @PostConstruct
  public void sendRequests() {
//    sendRequestsSequentially();

    sendRequestsParallel();
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
  public void sendRequestsParallel() {
    LOG.info("start sending requests in parallel");

    Flux<String> flux = sendMessagesParallel(List.of(15, 16, 17, 18, 19), waitInLoggerFor5sec);

    List<String> responses = flux.collectList().block();

    responses.stream().forEach(response -> LOG.info("result: {}", response));

    LOG.info("All messages send");
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
        return response.bodyToMono(String.class);
      } else if (response.statusCode().is4xxClientError()) {
        LOG.info("Error Payload: {}", value);
        return Mono.just("Error Response");
      } else if (response.statusCode().is5xxServerError()) {
        LOG.info("Exception Payload: {}", value);
        return response.bodyToMono(String.class);
      } else {
        return response.createException().flatMap(Mono::error);
      }
    });
    return response1;
  }

  Flux<String> sendMessagesParallel(List<Integer> messageValues, Boolean wait) {
    return Flux.fromIterable(messageValues).flatMap(value -> this.publishMessage(value, wait));
  }
}

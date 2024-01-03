package com.camunda.consulting.engine_spring_boot;

import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class ProcessInstanceStarter {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessInstanceStarter.class);

  private RuntimeService runtimeService;

  public ProcessInstanceStarter(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  @PostConstruct
  public void startProcessInstance() {
    List<ProcessInstance> runningInstances = runtimeService.createProcessInstanceQuery()
        .processDefinitionKey(ProcessConstants.PROCESS_DEFINITION_KEY).list();

    if (runningInstances.isEmpty()) {
      ProcessInstance instance = runtimeService.startProcessInstanceByKey(
          ProcessConstants.PROCESS_DEFINITION_KEY, "autostart", Map.of("starter", "demo"));
      LOG.info("process instance to test started: {}", instance.getProcessInstanceId());
    } else {
      LOG.info("There are process instances already running");
      runningInstances.stream()
          .forEach(instance -> LOG.info("Processdefinition {}: business key {}, {}",
              instance.getProcessDefinitionId(), instance.getBusinessKey(),
              instance.getProcessInstanceId()));
    }

  }
}

package com.camunda.consulting.rest_client_webflux;

/**
 * {
        "type": "Integer",
        "value": 16,
        "valueInfo": {},
        "id": "3e1a7af8-b475-11ee-b2ef-4ebeb350d073",
        "name": "var1",
        "processDefinitionId": "engine-spring-boot:2:09bcc94e-a9a1-11ee-87d5-de4660d7b4e0",
        "processInstanceId": "f3ab83da-b46e-11ee-9332-4ebeb350d073",
        "executionId": "3e196983-b475-11ee-b2ef-4ebeb350d073",
        "caseInstanceId": null,
        "caseExecutionId": null,
        "taskId": null,
        "batchId": null,
        "activityInstanceId": "Activity_1l4l977:4118edf8-b475-11ee-b2ef-4ebeb350d073",
        "errorMessage": null,
        "tenantId": null
    }
 */
public record Variable (
  String type,
  String value,
  Object valueInfo,
  String id,
  String name,
  String processDefinitionId,
  String processInstanceId,
  String executionId,
  String caseInstanceId,
  String caseExecutionId,
  String taskId,
  String batchId,
  String activityInstanceId,
  String errorMessage,
  String tenantId
) {}

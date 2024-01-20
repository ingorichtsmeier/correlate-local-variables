package com.camunda.consulting.rest_client_webflux;

/**
 * {
      "id": "41193c1c-b475-11ee-b2ef-4ebeb350d073",
      "name": "Confirm value",
      "assignee": "demo",
      "created": "2024-01-16T14:43:39.141+0100",
      "due": null,
      "followUp": null,
      "lastUpdated": null,
      "delegationState": null,
      "description": null,
      "executionId": "3e196983-b475-11ee-b2ef-4ebeb350d073",
      "owner": null,
      "parentTaskId": null,
      "priority": 50,
      "processDefinitionId": "engine-spring-boot:2:09bcc94e-a9a1-11ee-87d5-de4660d7b4e0",
      "processInstanceId": "f3ab83da-b46e-11ee-9332-4ebeb350d073",
      "taskDefinitionKey": "Activity_1l4l977",
      "caseExecutionId": null,
      "caseInstanceId": null,
      "caseDefinitionId": null,
      "suspended": false,
      "formKey": null,
      "camundaFormRef": null,
      "tenantId": null
  }
 */
public record Task (
  String id,
  String name,
  String assignee,
  String created,
  String due,
  String followUp,
  String lastUpdated,
  String delegationState,
  String description,
  String executionId,
  String owner,
  String parentTaskId,
  String priority,
  String processDefinitionId,
  String processInstanceId,
  String taskDefinitionKey,
  String caseExecutionId,
  String caseInstanceId,
  String caseDefinitionId,
  Boolean suspended,
  String formKey,
  String camundaFormRef,
  String tenantId
) {}  

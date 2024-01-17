package com.camunda.consulting.rest_client_webflux;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties
public class Variable {
  String type;
  String value;
  Object valueInfo;
  String id;
  String name;
  String processDefinitionId;
  String processInstanceId;
  String executionId;
  String caseInstanceId;
  String caseExecutionId;
  String taskId;
  String batchId;
  String activityInstanceId;
  String errorMessage;
  String tenantId;
  public Variable() {
    super();
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public Object getValueInfo() {
    return valueInfo;
  }
  public void setValueInfo(Object valueInfo) {
    this.valueInfo = valueInfo;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  public String getExecutionId() {
    return executionId;
  }
  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }
  public String getCaseInstanceId() {
    return caseInstanceId;
  }
  public void setCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
  }
  public String getCaseExecutionId() {
    return caseExecutionId;
  }
  public void setCaseExecutionId(String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
  }
  public String getTaskId() {
    return taskId;
  }
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
  public String getBatchId() {
    return batchId;
  }
  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }
  public String getActivityInstanceId() {
    return activityInstanceId;
  }
  public void setActivityInstanceId(String activityInstanceId) {
    this.activityInstanceId = activityInstanceId;
  }
  public String getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

}

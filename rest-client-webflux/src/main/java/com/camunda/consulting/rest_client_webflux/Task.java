package com.camunda.consulting.rest_client_webflux;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties
public class Task {
  String id;
  String name;
  String assignee;
  String created;
  String due;
  String followUp;
  String lastUpdated;
  String delegateState;
  String description;
  String executionId;
  String owner;
  String parentTaskId;
  String priority;
  String processDefinitionId;
  String processInstanceId;
  String taskDefinitionKey;
  String caseExecutionId;
  String caseInstanceId;
  String caseDefinitionId;
  Boolean suspend;
  String formKey;
  String camundFormRef;
  String tenantId;
  
  public Task() {
    //super();
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
  public String getAssignee() {
    return assignee;
  }
  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }
  public String getCreated() {
    return created;
  }
  public void setCreated(String created) {
    this.created = created;
  }
  public String getDue() {
    return due;
  }
  public void setDue(String due) {
    this.due = due;
  }
  public String getFollowUp() {
    return followUp;
  }
  public void setFollowUp(String followUp) {
    this.followUp = followUp;
  }
  public String getLastUpdated() {
    return lastUpdated;
  }
  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
  public String getDelegationState() {
    return delegateState;
  }
  public void setDelegationState(String delegateState) {
    this.delegateState = delegateState;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getExecutionId() {
    return executionId;
  }
  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }
  public String getOwner() {
    return owner;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }
  public String getParentTaskId() {
    return parentTaskId;
  }
  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }
  public String getPriority() {
    return priority;
  }
  public void setPriority(String priority) {
    this.priority = priority;
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
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }
  public void setTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }
  public String getCaseExecutionId() {
    return caseExecutionId;
  }
  public void setCaseExecutionId(String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
  }
  public String getCaseInstanceId() {
    return caseInstanceId;
  }
  public void setCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
  }
  public String getCaseDefinitionId() {
    return caseDefinitionId;
  }
  public void setCaseDefinitionId(String caseDefinitionId) {
    this.caseDefinitionId = caseDefinitionId;
  }
  public Boolean getSuspended() {
    return suspend;
  }
  public void setSuspended(Boolean suspend) {
    this.suspend = suspend;
  }
  public String getFormKey() {
    return formKey;
  }
  public void setFormKey(String formKey) {
    this.formKey = formKey;
  }
  public String getCamundaFormRef() {
    return camundFormRef;
  }
  public void setCamundaFormRef(String camundFormRef) {
    this.camundFormRef = camundFormRef;
  }
  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }
  
  
}

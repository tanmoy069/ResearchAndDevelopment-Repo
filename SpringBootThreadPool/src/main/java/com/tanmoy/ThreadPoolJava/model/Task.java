package com.tanmoy.ThreadPoolJava.model;

public class Task {

	private int id;
	private String taskName;
	private String taskType;
	private String details;

	public Task() {
	}

	public Task(int id, String taskName, String taskType, String details) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskType = taskType;
		this.details = details;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskType=" + taskType + ", details=" + details + "]";
	}

}

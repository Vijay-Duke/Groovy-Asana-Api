package org.GroovyAsanaApi.services;

import java.util.Set;

import org.GroovyAsanaApi.services.TaskBuilder.UpdateTaskBuilder;

import org.GroovyAsanaApi.Domain.Assignee_status;

public interface UpdateTask {

	public UpdateTask removeTaskFromProjectsList(String projectId);

	public UpdateTask addTaskToProjectsList(String projectId);

	public TaskBuilder build();

	public UpdateTaskBuilder updateAssignee(String assigneeId);

	public UpdateTaskBuilder updateName(String name);

	public UpdateTaskBuilder updateNotes(String notes);

	public UpdateTaskBuilder updateCompleted(boolean status);

	public UpdateTaskBuilder updateDue_on(String due_on);

	public UpdateTaskBuilder updateAssigneeStatus(
			Assignee_status assignee_status);

	public UpdateTaskBuilder addFollower(String followerId);

	public UpdateTaskBuilder addFollowerSet(Set<String> followerSet);

}

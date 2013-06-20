package org.GroovyAsanaApi.services;

import java.util.Set;

import org.GroovyAsanaApi.services.TaskBuilder.CreateBuilder;

import org.GroovyAsanaApi.Domain.Assignee_status;
import org.GroovyAsanaApi.Domain.User;

public interface AttachProject {

	public AttachProject attachTaskToProjectsByIdSet(Set<String> projectsIdSet);

	public AttachProject attachTaskToProjectSet(Set<String> projectsSet);

	public AttachProject attachTaskToProjects(String projectId);

	public TaskBuilder build();

	public CreateBuilder assignee(String assigneeId);

	public CreateBuilder name(String name);

	public CreateBuilder notes(String notes);

	public CreateBuilder completed(boolean status);

	public CreateBuilder due_on(String due_on);

	public CreateBuilder assigneeStatus(Assignee_status assignee_status);

	public CreateBuilder addFollower(String followerId);

	public CreateBuilder addFollowersIdSet(Set<String> followerSet);

	public CreateBuilder addFollowersSet(Set<User> followerSet);

	public CreateBuilder addNewTagsByCommaSeparatedNames(String tags);

	public CreateBuilder addExistingTagsById(Set<String> tagsSet);

}

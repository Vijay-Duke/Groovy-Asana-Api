package org.GroovyAsanaApi.Domain;

import groovy.json.JsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements Target{

	private String id;
	private List<Tag> tags = new ArrayList<Tag>();
	private List<User> followers = new ArrayList<User>();
	private Task parent;
	private String created_at;
	private String modified_at;
	private String name;
	private String notes;
	private User assignee;
	private boolean completed;
	private Assignee_status assignee_status;
	private String completed_at;
	private Date due_on;
	private List<Project> projects = new ArrayList<Project>();
	private List<SubTask> subTasks = new ArrayList<SubTask>();
	private Workspace workspace;

	public Task() {
	}

	public Task(Workspace workSpace, String taskName) {
		this.workspace = workSpace;
		this.name = taskName;
	}
	public Task(String workSpaceId, String taskName) {
		
		this.workspace = new Workspace(workSpaceId);
		this.name = taskName;
	}
	
	

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public String getId() {
		return id;
	}

	public List<Project> addTaskByProject(Project project) {
		projects.add(project);
		return projects;
	}

	public List<Project> removeTaskByProject(Project project) {
		projects.remove(project);
		return projects;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Assignee_status getAssignee_status() {
		return assignee_status;
	}

	public void setAssignee_status(Assignee_status assignee_status) {
		this.assignee_status = assignee_status;
	}

	public String getCompleted_at() {
		return completed_at;
	}

	public void setCompleted_at(String completed_at) {
		this.completed_at = completed_at;
	}

	public String getDue_on() {
		if (due_on != null)
			return new SimpleDateFormat("yyyy-MM-dd").format(due_on);
		else
			return null;
	}

	public void setDue_on(String due_on) {
		try {
			Date date;
			date = new SimpleDateFormat("yyyy-MM-dd").parse(due_on);
			this.due_on = date;
		} catch (Exception e) {
			this.due_on = null;
		}
	}

	@Override
	public String toString() {
		return "TO String : Task [id=" + id + ", tags=" + tags + ", workspace="
				+ workspace + ", followers=" + followers + ", parent=" + parent
				+ ", created_at=" + created_at + ", modified_at=" + modified_at
				+ ", name=" + name + ", notes=" + notes + ", assignee="
				+ assignee + ", completed=" + completed + ", assignee_status="
				+ assignee_status + ", completed_at=" + completed_at
				+ ", due_on=" + due_on + ", projects=" + projects
				+ ", subTasks=" + subTasks + "]";
	}

	public String toJson() {
		return new JsonBuilder(this).toString();
	}

}

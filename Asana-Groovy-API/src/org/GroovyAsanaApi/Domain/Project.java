package org.GroovyAsanaApi.Domain;

import groovy.json.JsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Project implements Target{
	private List<User> followers = new ArrayList<User>();
	private String notes;
	private Workspace workspace;
	private String id;
	private String name;
	private boolean archived;
	private String created_at;
	private String modified_at;
	
	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
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
	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Workspace getWorkSpace() {
		return workspace;
	}

	public void setWorkSpace(Workspace workSpace) {
		this.workspace = workSpace;
	}

	@Override
	public String toString() {
		return "to String : Project [id=" + id + ", name=" + name + ", archived="
				+ archived + ", created_at=" + created_at + ", modified_at="
				+ modified_at + "]";
	}
	
	public String toJson() {
		return new JsonBuilder(this).toString();
	}
}

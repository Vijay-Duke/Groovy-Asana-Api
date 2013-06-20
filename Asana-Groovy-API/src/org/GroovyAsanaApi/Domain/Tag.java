package org.GroovyAsanaApi.Domain;

import java.util.ArrayList;
import java.util.List;

public class Tag {

	private String id;
	private String created_at;
	private List<User> followers = new ArrayList<User>();
	private String name;
	private String notes;
	private Workspace workspace;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
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

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", created_at=" + created_at + ", followers="
				+ followers + ", name=" + name + ", notes=" + notes
				+ ", workspace=" + workspace + "]";
	}

}

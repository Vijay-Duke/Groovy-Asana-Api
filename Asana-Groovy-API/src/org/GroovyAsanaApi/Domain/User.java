package org.GroovyAsanaApi.Domain;

import groovy.json.JsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String id;
	private String name;
	private String email;
	private String photo;

	private List<Workspace> workspaces = new ArrayList<Workspace>();

	public String getEmail() {
		return email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", workspaces=" + workspaces + "]";
	}

	public String toJson() {
		return new JsonBuilder(this).toString();
	}
}

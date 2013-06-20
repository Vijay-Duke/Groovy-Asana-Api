package org.GroovyAsanaApi.Domain;

import groovy.json.JsonOutput;

public class Workspace {


	private String id;
	private String name;
	private boolean is_organization;

	public Workspace(){
		super();
	}
	public Workspace(String id) {
		this();
		this.id = id;
	}
	
	public boolean isIs_organization() {
		return is_organization;
	}

	public void setIs_organization(boolean is_organization) {
		this.is_organization = is_organization;
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
		return "WorkSpace [id=" + id + ", name=" + name + ", is_organization="
				+ is_organization + "]";
	}

	public String toJson() {
		return JsonOutput.toJson(this);
	}

}

package org.GroovyAsanaApi.Domain;

import groovy.json.JsonBuilder;

public class Story {
	private String id;
	private User created_by;
	private String created_at;
	private String text;
	private Tuple target;
	private String source;
	private String type;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Tuple getTarget() {
		return target;
	}

	public void setTarget(Tuple target) {
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the created_by
	 */
	public User getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	@Override
	public String toString() {
		return "Story [created_at=" + created_at + ", text=" + text
				+ ", target=" + target + ", source=" + source + ", type="
				+ type + "]";
	}
	
	public String toJson() {
		return new JsonBuilder(this).toString();
	}
}

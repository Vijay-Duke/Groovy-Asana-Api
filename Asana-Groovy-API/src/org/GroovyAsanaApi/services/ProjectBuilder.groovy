package org.GroovyAsanaApi.services

import groovy.transform.PackageScope;

import java.util.List;

import org.GroovyAsanaApi.Domain.User

class ProjectBuilder {
	private String openPostData;
	private String closePostData;
	private String postData;
	private boolean archived;
	private List<User> followers;
	private String name;
	private String notes;
	private String workspaceId;
	private String teamId;
	private List<String> putDataList = new ArrayList<String>();
	private List<String> followerList = new ArrayList<String>();
	private String projectId="";

	private ProjectBuilder(Builder b) {
		this.name = b.name;
		this.notes = b.notes;
		this.workspaceId = b.workspaceId;
		this.teamId = b.teamId;
		this.followers = b.followers;
		this.putDataList = b.putDataList;
		this.followerList = b.followerList;
		this.closePostData = b.closePostData;
		this.openPostData = b.openPostData;
		this.postData = b.postData;
		this.projectId = b.projectId;
	}

	public static class CreateBuilder extends ProjectBuilder.Builder{


		public CreateBuilder(String workspaceId) {
			createWithWorkspaceId(workspaceId);
		}
	}
	public static class UpdateBuilder extends ProjectBuilder.Builder{


		public UpdateBuilder(String projectId) {
			this.projectId= projectId;
		}
	}


	public static  class Builder{
		protected String openPostData ="""{"data": { """
		protected String closePostData ="""}} """
		protected String postData="";
		protected boolean archived;
		protected List<User> followers;
		protected String name;
		protected String notes;
		protected String workspaceId;
		protected String teamId;
		protected List<String> putDataList = new ArrayList<String>();
		protected List<String> followerList = new ArrayList<String>();
		String projectId="";
		boolean updateProject=false;

		public ProjectBuilder build() {
			/*if(!updateProject) {
				putDataList.add(""" "workspace":${workspaceId}  """);
			}*/
			return new ProjectBuilder(this);
		}
		protected Builder createWithWorkspaceId(String workspaceId) {
			this.workspaceId = workspaceId;
			putDataList.add(""" "workspace":${workspaceId}  """);
			return this;
		}
		public Builder archived(boolean archived) {
			putDataList.add(""" "archived":"${archived}"  """);
			this.archived = archived;
			return this;
		}
		protected Builder updateWithProjectId(String projectId) {
			this.projectId=projectId;
			return this;
		}

		public Builder name(String name) {
			putDataList.add(""" "name":"${name}"  """);
			return this;
		}
		public Builder notes(String notes) {
			putDataList.add(""" "notes":"${notes}"  """);
			return this;
		}
		public Builder team(String teamId) {
			putDataList.add(""" "team":${teamId}  """);
			return this;
		}
		public Builder addFollower(String followerId) {
			followerList.add(followerId);
			putDataList.add(""" "followers[${followerList.indexOf(followerId)}]":${followerId} """);
			return this;
		}
	}
	@PackageScope
	String getCreateProjectToPostFormat() {
		postData += openPostData
		String putData =""
		for (String s : putDataList) {
			putData += s + ",";
		}
		postData += putData.substring(0,putData.lastIndexOf(","));
		postData += closePostData
		return postData
	}
	@PackageScope
	String getProjectId() {
		return projectId;
	}
}


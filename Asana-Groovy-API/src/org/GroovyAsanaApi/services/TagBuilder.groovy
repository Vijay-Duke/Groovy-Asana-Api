package org.GroovyAsanaApi.services

import java.util.List;
public class TagBuilder {

	private String openPostData ="";
	private String closePostData="";
	private String postData="";
	private String name="";
	private String notes="";
	private String workspaceId;
	private List<String> putDataList = new ArrayList<String>();
	private String tagId;
	
	private TagBuilder(Builder b) {
		this.putDataList = b.putDataList;
		this.postData =b.postData ;
		this.notes = b.notes;
		this.name = b.name;
		this.tagId = b.tagId;
		this.workspaceId = b.workspaceId;
		this.openPostData =b.openPostData;
		this.closePostData =b.closePostData ;
		
	}
	public static class CreateBuilder extends TagBuilder.Builder{


		public CreateBuilder(String workspaceId) {
			createTagWithWorkspaceId(workspaceId);
		}
	}
	public static class UpdateBuilder extends TagBuilder.Builder{
		
		
				public UpdateBuilder(String tagId) {
					this.tagId= tagId;
				}
			}

	public static class Builder{
		protected String tagId="";
		protected String name="";
		protected String notes="";
		protected String workspaceId="";
		protected List<String> putDataList = new ArrayList<String>();
		protected String openPostData ="""{"data": { """
		protected String closePostData ="""}} """
		protected String postData="";
		
		protected Builder(){}
		
		public TagBuilder build() {
			return new TagBuilder(this);
		}
		protected TagBuilder.Builder  createTagWithWorkspaceId(String workspaceId) {
			this.workspaceId = workspaceId;
			putDataList.add(""" "workspace":${workspaceId}  """);
			return this;
		}

		public TagBuilder.Builder name(String name) {
			this.name =name;
			putDataList.add(""" "name":"${name}"  """);
			return this;
		}
		public TagBuilder.Builder notes(String notes) {
			this.notes=notes;
			putDataList.add(""" "notes":"${notes}"  """);
			return this;
		}
	}

	String  getCreateTagToPostFormat() {
		postData += openPostData
		String putData =""
		for (String s : putDataList) {
			putData += s + ",";
		}
		postData += putData.substring(0,putData.lastIndexOf(","));
		postData += closePostData
		return postData
	}
	String  getTaskId() {
		return tagId;
	}

	@Override
	public String toString() {
		return "Builder [name=" + name + ", notes=" + notes + ", workspaceId=" + workspaceId + ", putDataList="+ putDataList + ", openPostData=" + openPostData+ ", closePostData=" + closePostData + ", postData="+ postData + "]";
	}
}


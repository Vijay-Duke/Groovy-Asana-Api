package org.GroovyAsanaApi.services

import java.io.IOException;

import org.GroovyAsanaApi.Domain.Project;
import org.GroovyAsanaApi.Domain.Tag
import org.GroovyAsanaApi.Domain.Task
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;
import org.GroovyAsanaApi.Exception.WorkspaceException

import groovy.json.JsonSlurper

class TagOperations {

	String postData="";

	private Asana operator;
	public final String TAG_URL ="/tags";
	public final String WORKSPLACES_URL ="/workspaces/"

	public TagOperations(Asana Operator) {
		this.operator = Operator;
	}


	public Tag createTag(TagBuilder tagBuilder) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String postData = tagBuilder.getCreateTagToPostFormat();
		def returnedData =operator.postMethod(TAG_URL, postData)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		Tag  JsonTag =jsonObj.data
		return JsonTag
	}
	public Tag updateTag(TagBuilder tagBuilder) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String tagId = tagBuilder.getTaskId();
		String postData = tagBuilder.getCreateTagToPostFormat();
		if(tagId.length()<=0 )
			throw new WorkspaceException("Tag Id IS MANDATORY !");
		def returnedData =operator.putMethod(TAG_URL+"/${tagId}", postData)  
		def jsonObj = new JsonSlurper().parseText( returnedData)
		Tag  JsonTag =jsonObj.data
		return JsonTag
	}

	public List<Task> getTasksByTag(String tagId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String getData = operator.getMethod(TAG_URL+"/${tagId}/tags");
		def jsonObj = new JsonSlurper().parseText( getData)
		Task  JsonTag =jsonObj.data
		List<Task> tasks = new ArrayList<Task>();
		JsonTasks.each  { it ->
			Task t = it
			t =getTaskById(t.id)
			tasks.add(t)
		}
		return tasks;
	}

	public Tag getTagById(String tagId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String getData = operator.getMethod(TAG_URL+"/${tagId}");
		def jsonObj = new JsonSlurper().parseText( getData)
		Tag  JsonTag =jsonObj.data
		return JsonTag
	}
	public List<Tag> getAllTags() throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String getData = operator.getMethod(TAG_URL);
		def jsonObj = new JsonSlurper().parseText( getData)
		Tag  JsonTag =jsonObj.data
		List<Tag> tags = new ArrayList<Tag>();
		tags.each  { it ->
			Tag t = it
			t =getTagById(t.id)
			tags.add(t)
		}
		return tags;
	}
	public List<Tag> getAllTagsInWorkSpace(String workSpaceId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String getData = operator.getMethod("/workspaces/${workSpaceId}"+TAG_URL);
		def jsonObj = new JsonSlurper().parseText( getData)
		Tag  JsonTag =jsonObj.data
		List<Tag> tags = new ArrayList<Tag>();
		tags.each  { it ->
			Tag t = it
			t =getTagById(t.id)
			tags.add(t)
		}
		return tags;
	}
	public Task addTagToTask(String tagId, String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		if(tagId.length()<1)
			return operator.TaskOperations().getTaskById(taskId);
		String getData = operator.postMethod("/tasks/${taskId}/addTag","""{"data":{"tag":${tagId} }}""");
		def jsonObj = new JsonSlurper().parseText( getData)
		return operator.TaskOperations().getTaskById(taskId);
	}

}
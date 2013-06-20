package org.GroovyAsanaApi.services

import java.io.IOException;

import org.GroovyAsanaApi.Domain.Story;
import org.GroovyAsanaApi.Domain.Task
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

import groovy.json.JsonSlurper

class StoriesOperations {

	private Asana operator;
	public final String STORIES_URL ="/stories";
	public final String TASK_URL ="/tasks";
	public final String PROJECT_URL ="/projects/";

	public StoriesOperations(Asana Operator) {
		this.operator = Operator;
	}
	
	public List<Story> getStoriesForTask(String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		def returnedData =operator.getMethod(TASK_URL+"/${taskId}"+STORIES_URL)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonStorys =jsonObj.data
		List<Story> stories = new ArrayList<Story>();
		JsonStorys.each  { it ->
			Story t = it
			t =getStoryById(t.getId());
			stories.add(t)
		}
		return stories;
		
	}
	public List<Story> getStoriesForProject(String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		def returnedData =operator.getMethod(PROJECT_URL+"/${projectId}"+STORIES_URL)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonStorys =jsonObj.data
		List<Story> stories = new ArrayList<Story>();
		JsonStorys.each  { it ->
			Story t = it
			t =getStoryById(t.getId());
			stories.add(t)
		}
		return stories;
	}
	public Story getStoryById(String storyId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		def returnedData =operator.getMethod(STORIES_URL+"/${storyId}")
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonStory =jsonObj.data
		Story s = JsonStory
		return s;
	}
	public Story CommentOnTask(String taskId,String comment) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		def returnedData =operator.postMethod(TASK_URL+"/${taskId}"+STORIES_URL,"""{"data":{ "text":"${comment}" } } """)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonStory =jsonObj.data
		Story s = JsonStory
		return s;
		
	}
	public Story CommentOnProject(String projectId,String comment) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		def returnedData =operator.postMethod(PROJECT_URL+"/${projectId}"+STORIES_URL,"""{"data":{ "text":"${comment}" } } """)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonStory =jsonObj.data
		Story s = JsonStory
		return s;
	}
}

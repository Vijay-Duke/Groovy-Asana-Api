package org.GroovyAsanaApi.services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.classgen.ReturnAdder;

import org.GroovyAsanaApi.Domain.Project;
import org.GroovyAsanaApi.Domain.SubTask;
import org.GroovyAsanaApi.Domain.Tag;
import org.GroovyAsanaApi.Domain.Task
import org.GroovyAsanaApi.Domain.User;
import org.GroovyAsanaApi.Domain.Workspace;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.NullTaskIdException
import org.GroovyAsanaApi.Exception.NullTaskNameException
import org.GroovyAsanaApi.Exception.NullWorkSpaceException
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class TaskOperations {


	String openPostData ="""{"data": { """
	String closePostData ="""}} """
	String postData="";
	public  final String NullNullTaskIdExceptionMessage ="Tasks ID is Mandatory"
	public  final String NullWorkSpaceExceptionMessage="WorkSpace is Mandatory and WorkSpace ID Cannot be Null"
	private Asana operator;
	public final String TASK_URL ="/tasks";
	public final String WORKSPLACES_URL ="/workspaces/"
	public TaskOperations(Asana Operator) {
		this.operator = Operator;
	}

	public Task createTask(TaskBuilder taskCreator) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String putData = taskCreator.getUpdateTaskToPutFormat();
		def returnedData =operator.postMethod(TASK_URL, putData)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		Task  JsonTask =jsonObj.data
		JsonTask = addNewTags(taskCreator.getNewTags(),JsonTask.getId());
		JsonTask = addExistingTags(taskCreator.getExistingTags(), JsonTask.getId());
		return JsonTask
	}

	public void addProjectToTask(String taskId, String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		operator.postMethod(TASK_URL+"/${taskId}/addProject",""" {"data":{ "project":${projectId} } } """)
	}

	public void removeProjectFromTask(String taskId, String projectId) {
		operator.postMethod(TASK_URL+"/${taskId}/removeProject",""" {"data":{ "project":${projectId} } } """)
	}
	public void updateTask(TaskBuilder taskBuilder) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		List<String> addProjectList = taskBuilder.getAddProjectList();
		List<String> removeProjectList = taskBuilder.getRemoveProjectList();
		String putData = taskBuilder.getUpdateTaskToPutFormat();   
		def returnedData =operator.putMethod(TASK_URL+"/"+taskBuilder.taskId, putData)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		for(String projectId : addProjectList)
		{
			addProjectToTask(taskBuilder.taskId,projectId);
		}
		for(String projectId : removeProjectList)
		{
			removeProjectFromTask(taskBuilder.taskId,projectId);
		}
		Task  JsonTask =jsonObj.data
		JsonTask = addNewTags(taskBuilder.getNewTags(),taskBuilder.taskId);
		JsonTask = addExistingTags(taskBuilder.getExistingTags(), taskBuilder.taskId);
	}


	private Task addNewTags(List<String> tagNames,String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		List<String> tags = new ArrayList<String>();
		String id =getTaskById(taskId).getWorkspace().getId() ;
		for(String tag : tagNames)
		{
			TagBuilder tagBuilder = new TagBuilder.CreateBuilder(id)
					.name(tag).build();
			 Tag t =operator.TagOperations().createTag(tagBuilder);
			 tags.add(t.getId());
		}
		return addExistingTags(tags,taskId);
	}
	private Task addExistingTags(List<String> tagIds,String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		for(String tag : tagIds)
		{

			operator.TagOperations().addTagToTask(tag, taskId)
		}
		return getTaskById(taskId);
	}

	public List<Tag> getAllTagsForTask(String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  tagData =operator.getMethod(TASK_URL+"/${taskId}/tags" );
		def jsonObj = new JsonSlurper().parseText( tagData)
		def  JsonTags =jsonObj.data
		List<Tag> tags = new ArrayList<Tag>();
		JsonTags.each  { it ->
			Tag t = it
			t =operator.TagOperations().getTagById(t.id)
			tags.add(t)
		}
		return tags;
	}
	
	
	
	public Task addNewTagsToTask(String commaSeparatedNewTagNames,String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		List<String> tags = new ArrayList<String>();
		tags.addAll(commaSeparatedNewTagNames.split(","));
		addNewTags(tags, taskId);
	}
	
	public void addTagToTask(String taskId, String tagId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  tagData =operator.postMethod(TASK_URL+"/${taskId}/addTag",""" {"data":{ "tag":${tagId} } } """ );
	}
	public void removeTagToTask(String taskId, String tagId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  tagData =operator.postMethod(TASK_URL+"/${taskId}/removeTag",""" {"data":{ "tag":${tagId} } } """ );
	}

	public void addFollowerToTask(String taskId, String followerId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  tagData =operator.postMethod(TASK_URL+"/${taskId}/addFollowers",""" {"data":{ "followers[0]":${followerId} } } """ );
	}
	public void removeFollowerFromTask(String taskId, String followerId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  tagData =operator.postMethod(TASK_URL+"/${6069876235704}/removeFollowers",""" {"data":{ "followers[0]":${5690619671404} } } """);
	}

	public Task getTaskById(String id)
	{
		String 	taskUrl =  "/tasks/"+id;
		String  userData = operator.getMethod(taskUrl);
		def JsonObject = new JsonSlurper().parseText( userData );
		return (Task)JsonObject.data;
	}


	public List<Task> getAllTasksOfAllUsersInWorkspace(String workspaceId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		if(workspaceId == null)
			throw new NullWorkSpaceException(NullWorkSpaceExceptionMessage);
		List<Task> tasks = new ArrayList<Task>();
		operator.UserOperations().getAllUser().each{ user ->
			User u = (User)user
			tasks.addAll(getAllTasksInWorkspaceForUserById(workspaceId,u.getId()))
		}
		return tasks;
	}
	public List<Task> getAllTasksInWorkspaceForUserById(String workspaceId,String userId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  taskData =operator.getMethod("""/tasks?workspace=${workspaceId}&assignee=${userId}""" );
		def jsonObj = new JsonSlurper().parseText( taskData)
		def  JsonTasks =jsonObj.data
		List<Task> tasks = new ArrayList<Task>();
		JsonTasks.each  { it ->
			Task t = it
			t =getTaskById(t.id)
			tasks.add(t)
		}
		return tasks;
	}


	public List<Task> getAllTasksInWorkspaceAssignedToMe(String workspaceId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		return getAllTasksInWorkspaceForUserById(workspaceId,"me");
	}

	public Task getTask(Task task)
	{
		if(task.getId() == null)
			throw new NullTaskIdException(NullNullTaskIdExceptionMessage)
		return	getTaskById(task.getId())
	}


	public void deleteTask(String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		operator.deleteMethod(TASK_URL+"/${taskId}");
	}

	public List<Project> getAllProjectsForTask(String taskId)
	{
		String  taskData =operator.getMethod("/tasks/${taskId}/projects" );
		def jsonObj = new JsonSlurper().parseText( taskData)
		def  JsonTasks =jsonObj.data
		List<Project> projects = new ArrayList<Project>();
		JsonTasks.each  { it ->
			Project t = it
			t =operator.ProjectOperations().getProjectById(t.id)
			projects.add(t)
		}
		return projects;
	}

	public List<Task> getSubTasks(String taskId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  taskData =operator.getMethod("/tasks/${taskId}/subtasks" );
		def jsonObj = new JsonSlurper().parseText( taskData)
		def  JsonTasks =jsonObj.data
		List<Task> tasks = new ArrayList<Task>();
		JsonTasks.each  { it ->
			Task t = it
			t =getTaskById(t.id)
			tasks.add(t)
		}
		return tasks;
	}

	public Task setParentForTask(String taskId,String parentId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{

		String  taskData =operator.postMethod("/tasks/${taskId}/setParent",""" {"data":{"parent":${parentId}}} """ );
		def jsonObj = new JsonSlurper().parseText( taskData)
		def  ParentJsonTasks =jsonObj.data
		Task t = ParentJsonTasks
		return t;
	}

	public List<Task> getTaskForProject(String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		return operator.ProjectOperations().getTasksForProject(projectId);
	}

	public List<Task> getTasksWithTag(String tagId)
	{
		return  operator.TagOperations().getTasksByTag(tagId);
	}




}

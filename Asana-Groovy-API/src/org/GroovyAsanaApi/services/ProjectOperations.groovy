package org.GroovyAsanaApi.services

import java.io.IOException;

import org.GroovyAsanaApi.Domain.Project
import org.GroovyAsanaApi.Domain.Task;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.ProjectUpdateException
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

import groovy.json.JsonSlurper

class ProjectOperations {
	private Asana operator;
	public final String PROJECT_URL ="/projects";
	public final String WORKSPACE_URL ="/workspaces";

	public ProjectOperations(Asana Operator) {
		this.operator = Operator;
	}

	public Project createProject(ProjectBuilder projectCreator) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String putData = projectCreator.getCreateProjectToPostFormat();
		def returnedData =operator.postMethod(PROJECT_URL, putData)
		def jsonObj = new JsonSlurper().parseText( returnedData)
		Project  JsonProject =jsonObj.data
		return JsonProject
	}

	public Project updateProject(ProjectBuilder projectCreator) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String putData = projectCreator.getCreateProjectToPostFormat();
		if(projectCreator.getProjectId() == null) {
			throw new ProjectUpdateException("Cannot Update Project. Project Id is Mandatory, use Method:updateWithProjectId() of Class: ProjectCreator");
		}
		else {
			String returnedData =operator.putMethod(PROJECT_URL+"/${projectCreator.projectId}", putData)
			def jsonObj = new JsonSlurper().parseText(returnedData)
			Project  JsonProject =jsonObj.data
			return JsonProject
		}
	}
	public Project getProjectById(String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		def returnedData =operator.getMethod(PROJECT_URL+"/${projectId}")
		def jsonObj = new JsonSlurper().parseText( returnedData)
		def  JsonProjects =jsonObj.data
		Project projects = JsonProjects
		return projects;
	}
	public void deleteProject(String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		def returnedData =operator.deleteMethod(PROJECT_URL+"/${projectId}")
	}

	public List<Task> getTasksForProject(String projectId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String  taskData =operator.getMethod(PROJECT_URL+"/${projectId}/tasks" );
		def jsonObj = new JsonSlurper().parseText( taskData)
		def  JsonTasks =jsonObj.data
		List<Task> tasks = new ArrayList<Task>();
		JsonTasks.each  { it ->
			Task t = it
			t =operator.TaskOperations().getTaskById(t.getId());
			tasks.add(t)
		}
		return tasks;
	}
	public List<Project> getAllProjects() throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  projectData =operator.getMethod(PROJECT_URL);
		def jsonObj = new JsonSlurper().parseText( projectData)
		def  JsonProjects =jsonObj.data
		List<Project> projects = new ArrayList<Project>();
		JsonProjects.each  { it ->
			Project project = it
			project =getProjectById(project.getId());
			projects.add(project)
		}
		return projects;
	}
	public List<Project> getProjectsInWorkspace(String workSpaceId) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException
	{
		String  projectData =operator.getMethod("/workspaces/${workSpaceId}"+PROJECT_URL);
		def jsonObj = new JsonSlurper().parseText( projectData)
		def  JsonProjects =jsonObj.data
		List<Project> projects = new ArrayList<Project>();
		JsonProjects.each  { it ->
			Project project = it
			project =getProjectById(project.getId());
			projects.add(project)
		}
		return projects;
	}
}

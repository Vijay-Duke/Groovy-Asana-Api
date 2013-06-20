package org.GroovyAsanaApi.services;

import groovy.json.JsonSlurper

import java.io.IOException;
import java.util.List;

import org.GroovyAsanaApi.Domain.Workspace;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

public class WorkspaceOperations {

	private Asana operator;
	private final String organizations_url = "/organizations";
	private final String WORKSPACE_URL = "/workspaces";
	private final String TEAM_URL = "/teams";

	public WorkspaceOperations(Asana Operator) {
		this.operator = Operator;
	}

	public List<Workspace> getAllWorkspaces() throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String returnedData =operator.getMethod(WORKSPACE_URL);
		def jsonObj = new JsonSlurper().parseText(returnedData)
		List<Workspace> workspaces = new ArrayList<Workspace>();
		jsonObj.data.each { it ->
			Workspace w = it;
			workspaces.add(w);
		}
		return workspaces
	}
	
	public Workspace updateName(String workspaceId,String name) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String returnedData =operator.putMethod(WORKSPACE_URL+"/${workspaceId}","""{"data":{ "name":"${name}" }}""");
		def jsonObj = new JsonSlurper().parseText(returnedData)
		Workspace  JsonProject =jsonObj.data
		return JsonProject
	}
}

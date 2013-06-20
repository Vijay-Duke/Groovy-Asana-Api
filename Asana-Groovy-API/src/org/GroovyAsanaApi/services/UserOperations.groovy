package org.GroovyAsanaApi.services

import groovy.json.JsonSlurper

import java.io.IOException;
import java.util.List;

import org.GroovyAsanaApi.Domain.User;
import org.GroovyAsanaApi.Domain.Workspace;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;



class UserOperations {


	private final String USER_URL ="/users";
	private final String OPTIONS="?opt_fields=name,email"
	private final String WORKSPLACES_URL ="/workspaces/";
	private final String USERS ="/users";
	private List<Workspace> workspaces
	private Asana operator;
	public List<User> getAllUser() throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String 	allUserUrl =  USER_URL+OPTIONS;
		String  userData =operator.getMethod(allUserUrl);
		def jsonObj = new JsonSlurper().parseText( userData )
		List<User> Jsonusers = jsonObj.data
		List<User> users = new ArrayList<User>();
		for(User JsonUser : Jsonusers) {
			users.add(JsonUser);
		}
		return users;
	}
	public User getUser() throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String 	userUrl =  USER_URL+"/me";
		String  userData = operator.getMethod(userUrl);
		def JsonObject = new JsonSlurper().parseText( userData );
		return (User)JsonObject.data;
	}

	public List<User> getAllUserInWorkspace(String id) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String 	allUserInWorkSpaceUrl =  WORKSPLACES_URL+id+USERS;
		String  userData =operator.getMethod(allUserInWorkSpaceUrl);
		def jsonObj = new JsonSlurper().parseText( userData )
		List<User> JsonUsers = jsonObj.data
		List<User> users = new ArrayList<User>();
		for(User JsonUser : JsonUsers) {
			users.add(JsonUser);
		}
		return users;
	}

	public User getUser(String id) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		String 	userUrl =  USER_URL+"/"+id;
		String  userData = operator.getMethod(userUrl);
		def JsonObject = new JsonSlurper().parseText( userData );
		return (User)JsonObject.data;
	}
}

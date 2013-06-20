package org.GroovyAsanaApi.services;

import org.GroovyAsanaApi.Domain.User
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

import groovy.json.JsonSlurper
import groovy.transform.PackageScope;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import org.codehaus.groovy.runtime.EncodingGroovyMethods

public class Asana {
	private  final String url = "https://app.asana.com/api/";
	private final String apiUrl = url + "1.0";
	private final String USER_URL ="/users/";
	private final String WORKSPLACES_URL ="/worlspaces/";
	private final String API_URL="";
	private UserOperations userOperations;
	private TaskOperations taskOperations;
	private ProjectOperations projectOperations;
	private TagOperations tagOperations;
	private StoriesOperations storiesOperations;
	private WorkspaceOperations workspaceOperations;
	private String basicAuthenticationKey ;
	public Asana(String key) {
		basicAuthenticationKey = (key+":").bytes.encodeBase64().toString()
		userOperations = new UserOperations(this);
		taskOperations = new TaskOperations(this);
		projectOperations = new ProjectOperations(this);
		tagOperations = new TagOperations(this);
		storiesOperations = new StoriesOperations(this);
		workspaceOperations = new WorkspaceOperations(this);
	}

	public UserOperations UserOperations() {
		return userOperations;
	}
	public StoriesOperations StoriesOperations() {
		return storiesOperations;
	}
	public TaskOperations TaskOperations() {
		return taskOperations;
	}
	public ProjectOperations ProjectOperations() {
		return projectOperations;
	}
	public TagOperations TagOperations() {
		return tagOperations;
	}
	public WorkspaceOperations WorkspaceOperations() {
		return workspaceOperations;
	}
	@PackageScope
	String getMethod(String RESTurl) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		return httpUrlConnector(RESTurl,"","GET")
	}
	@PackageScope
	String postMethod(String RESTurl,writeData) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		return httpUrlConnector(RESTurl,writeData,"POST")
	}
	@PackageScope
	String putMethod(String RESTurl,writeData) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		return httpUrlConnector(RESTurl,writeData,"PUT")
	}
	@PackageScope
	String deleteMethod(String RESTurl) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException{
		return httpUrlConnector(RESTurl,"","DELETE")
	}

	private String httpUrlConnector(String RESTurl,String writeData,String methodType) throws RateLimitEnforcedException,InvalidRequestException,NoAuthorizationException,ForbiddenException,NotFoundException,IOException {
		String data = null;
		InputStream inStream;
		URL url = new URL(apiUrl+RESTurl);
		HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();

		urlConn.setRequestMethod(methodType);
		urlConn.setAllowUserInteraction(false);
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setRequestProperty("Content-type", "application/json");
		urlConn.setRequestProperty("authorization",  "Basic " + basicAuthenticationKey);
		urlConn.setReadTimeout(2*1000)
		if(writeData.length()>4) {
			OutputStreamWriter outStream = new OutputStreamWriter(urlConn.getOutputStream());
			outStream.write(writeData)
			outStream.flush();
			outStream.close();
		}
		int responseCode = urlConn.getResponseCode();
		try{
			inStream =  new DataInputStream(urlConn.getInputStream());
			data = httpStreamToString(inStream);
		}catch(IOException e) {
			inStream = new DataInputStream(urlConn.getErrorStream());
			data = httpStreamToString(inStream);
			if(responseCode.value==400)
			throw new InvalidRequestException("Invalid Request. ! : ${data} ");
			if(responseCode.value==401)
			throw new NoAuthorizationException("No authorization. ! :${data}");
			if(responseCode.value==403)
			throw new ForbiddenException("Forbidden. ! : ${data}");
			if(responseCode.value==404)
			throw new NotFoundException("Not found ! : ${data}");
			if(responseCode.value==429)
			throw new RateLimitEnforcedException("Rate Limit Enforced ! : ${data}");
		}
		finally{
			inStream.close();
		}

		return data;
	}

	private String httpStreamToString(InputStream inputStream) {

		try {
			return new Scanner(inputStream).useDelimiter("\\A").next();
		} catch (NoSuchElementException e) {
			return "";
		}
	}
}

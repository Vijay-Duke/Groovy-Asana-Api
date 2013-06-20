package org.GroovyAsanaApi.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.GroovyAsanaApi.services.Asana;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import org.GroovyAsanaApi.Domain.Project;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

public class StoriesOperationsTest {
	Asana asana = new Asana("API_KEY");
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStoriesOperations() {
		asana.StoriesOperations().toString();
	}

	@Test
	public void testGetStoriesForTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	}

	@Test
	public void testGetStoriesForProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	List<Project> projects=	asana.ProjectOperations().getAllProjects();
	}

	@Test
	public void testGetStoryById() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	}

	@Test
	public void testCommentOnTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	asana.StoriesOperations().CommentOnTask("6074301914699", "Hello World");
	}

	@Test 
	public void testCommentOnProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.StoriesOperations().CommentOnProject("6074301914699", "Hello World");
	}

}

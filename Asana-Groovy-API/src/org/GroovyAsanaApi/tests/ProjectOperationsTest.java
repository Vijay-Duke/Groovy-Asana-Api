package org.GroovyAsanaApi.tests;

import java.io.IOException;

import org.GroovyAsanaApi.Domain.Project;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;
import org.GroovyAsanaApi.services.Asana;
import org.GroovyAsanaApi.services.ProjectBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectOperationsTest {

	Asana asana = new Asana("API_KEY");
	static Project pt;
	public Project p;
	@Before
	public void setUp() throws Exception {
		ProjectBuilder projectCreator = new ProjectBuilder.CreateBuilder("5845709905944").name("Just Fluf").notes("No Stuff").addFollower("5690619671404").build();
		 p =asana.ProjectOperations().createProject(projectCreator);
	}

	@After
	public void tearDown() throws Exception {
		asana.ProjectOperations().deleteProject(p.getId());
	}
	
	@Test
	public void testCreateProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		ProjectBuilder projectCreator = new ProjectBuilder.CreateBuilder("5845709905944").name("Just Fluf").notes("No Stuff").addFollower("5690619671404").build();
		Project p =asana.ProjectOperations().createProject(projectCreator);
		pt =p;
	}

	@Test
	public void testUpdateProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		ProjectBuilder projectUpdater = new ProjectBuilder.UpdateBuilder(p.getId()).name("Just Fluf Updated Twice").archived(false).notes("No Stuff").addFollower("5690619671404").build();
		asana.ProjectOperations().updateProject(projectUpdater);
	}

	@Test
	public void testGetProjectById() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		p = asana.ProjectOperations().getProjectById(p.getId());
	}

	@Test
	public void testDeleteProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.ProjectOperations().deleteProject("5845731550386");
	}

	@Test
	public void testGetTasksForProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	}

	@Test
	public void testGetProjects() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	asana.ProjectOperations().getAllProjects();
	}

	@Test
	public void testGetProjectsInWorkspace() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
	}

}

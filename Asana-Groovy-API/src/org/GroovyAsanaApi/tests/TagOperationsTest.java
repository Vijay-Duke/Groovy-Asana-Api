package org.GroovyAsanaApi.tests;

import java.io.IOException;

import org.GroovyAsanaApi.services.Asana;
import org.GroovyAsanaApi.services.TagBuilder;
import org.junit.After;
import org.junit.Test;

import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

public class TagOperationsTest {
	Asana asana = new Asana("API_KEY");

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTaskOperations() {

		asana.TagOperations().toString();
	}

	@Test
	public void testCreateTag() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {

		TagBuilder tagBuilder = new TagBuilder.CreateBuilder("5845709905944")
				.name("Tag 2").notes("Noitesz 2").build();

	}

	@Test
	public void testUpdateTag() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		TagBuilder tagBuilder = new TagBuilder.UpdateBuilder("5920915555977")
				.name("updated Tag 2").notes("Noitesz 2").build();

	}

	@Test
	public void testGetTaskByTag() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.TagOperations().getTasksByTag("");
	}

	@Test
	public void testGetTagById() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.TagOperations().getTagById("5850068963682");
	}

	@Test
	public void testGetAllTags() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.TagOperations().getAllTags();
	}

	@Test
	public void testGetAllTagsInWorkSpace() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		asana.TagOperations().getAllTagsInWorkSpace("5845709905944");
	}

}

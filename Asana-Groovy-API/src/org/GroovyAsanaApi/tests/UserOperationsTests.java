package org.GroovyAsanaApi.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.GroovyAsanaApi.services.Asana;
import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.runners.Parameterized.Parameters;

import org.GroovyAsanaApi.Domain.User;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;

public class UserOperationsTests {
	Asana asana = new Asana("1EUSjL1y.lO0jmAQEYB60l5wt8HKQdEj");

	@Test
	public void getAllUserTest() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		List<User> users = asana.UserOperations().getAllUser();
		for (User user : users) {

		}
	}

	@Test
	public void getUser() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		User user = asana.UserOperations().getUser();
	}

	@Test
	public void TestGetUserWithId() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		this.getUser("5845730099300");
	}

	public void getUser(String id) throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		User user = asana.UserOperations().getUser(id);
	}

	@Test
	public void TestGetUser() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		this.getAllUserInWorkspaceTest("5845709905944");
	}

	public void getAllUserInWorkspaceTest(String id) throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		List<User> users = asana.UserOperations().getAllUserInWorkspace(id);
		for (User user : users) {
		}
	}

}

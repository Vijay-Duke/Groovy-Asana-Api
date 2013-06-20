package org.GroovyAsanaApi.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.GroovyAsanaApi.Domain.Project;
import org.GroovyAsanaApi.Domain.Tag;
import org.GroovyAsanaApi.Domain.Task;
import org.GroovyAsanaApi.Domain.User;
import org.GroovyAsanaApi.Domain.Workspace;
import org.GroovyAsanaApi.Exception.ForbiddenException;
import org.GroovyAsanaApi.Exception.InvalidRequestException;
import org.GroovyAsanaApi.Exception.NoAuthorizationException;
import org.GroovyAsanaApi.Exception.NotFoundException;
import org.GroovyAsanaApi.Exception.RateLimitEnforcedException;
import org.GroovyAsanaApi.Exception.WorkspaceException;
import org.GroovyAsanaApi.services.Asana;
import org.GroovyAsanaApi.services.TagBuilder;
import org.GroovyAsanaApi.services.TaskBuilder;
import org.GroovyAsanaApi.services.TaskOperations;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TaskOperationsTest {

	static Asana asana;
	static TaskOperations taskOperator;
	static Workspace workspace;
	static User owner;
	static List<User> followers;
	static List<Project> projects;
	static List<Task> allTasks;
	static Task task;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 asana = new Asana("1EUSjL1y.eP1S02i0fou3jTc00lmCHDP");
		taskOperator = asana.TaskOperations();
		workspace = asana.WorkspaceOperations().getAllWorkspaces().get(0);
		owner = asana.UserOperations().getUser();
		followers = asana.UserOperations().getAllUser();
		projects = asana.ProjectOperations().getAllProjects();

	}

	public static void updateAllTasks() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		allTasks = taskOperator.getAllTasksInWorkspaceAssignedToMe(workspace.getId());

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		updateAllTasks();
		task = allTasks.get(0);
		/*
		 * TaskBuilder createTask = new TaskBuilder.CreateSubTaskBuilder(
		 * task.getId()).addFollowersSet(new HashSet<User>(followers))
		 * .assignee(owner.getId()).name("Test Task").notes("Test Notesz")
		 * .addNewTagsByCommaSeparatedNames("Tag1,Tag2,Tag3").build(); task =
		 * taskOperator.createTask(createTask);
		 */

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTaskOperations() {
		assertNotNull(taskOperator);
	}

	@Test
	public void testCreateTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		TaskBuilder createTask = new TaskBuilder.CreateBuilder(
				workspace.getId())
				.addFollowersSet(new HashSet<User>(followers))
				.assignee(owner.getId()).name("Test Task").notes("Test Notesz")
				.addNewTagsByCommaSeparatedNames("Tag1,Tag2,Tag3").build();
		Task t = taskOperator.createTask(createTask);
		assertTrue(t.getAssignee().getId().equals(owner.getId()));
		assertTrue(t.getWorkspace().getId().equals(workspace.getId()));
		assertTrue(t.getName().equals("Test Task"));
		assertTrue(t.getNotes().equals("Test Notesz"));
		task = t;

	}

	@Test
	public void testAddProjectToTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		int previousSize = allTasks.get(0).getProjects().size();
		taskOperator.addProjectToTask(task.getId(), projects.get(1).getId());
		assertEquals(previousSize,taskOperator.getTaskById(allTasks.get(0).getId()).getProjects().size()-1);
	}

	@Test
	public void testRemoveProjectFromTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		taskOperator.addProjectToTask(task.getId(), projects.get(0).getId());
		taskOperator.removeProjectFromTask(task.getId(), projects.get(0)
				.getId());
		for (Project p : task.getProjects()) {
			assertNotEquals(p.getId(), task.getId());
		}

	}

	@Test
	public void testUpdateTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		String updateName ="Update Name";
		TaskBuilder taskBuilder = new TaskBuilder.UpdateTaskBuilder(
				task.getId()).updateName(updateName)
				.build();
		taskOperator.updateTask(taskBuilder);
		assertEquals(taskOperator.getTaskById(task.getId()).getName(), updateName);
	}

	@Test
	public void testGetAllTagsForTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {

	}

	@Test
	public void testAddTagToTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		TagBuilder tagBuilder = new TagBuilder.CreateBuilder(workspace.getId())
				.name("New Tag").build();
		Tag t = asana.TagOperations().createTag(tagBuilder);
		int previousSize = allTasks.get(0).getTags().size();
		taskOperator.addTagToTask(allTasks.get(0).getId(), t.getId());
		assertEquals(previousSize + 1,
				taskOperator.getTaskById(allTasks.get(0).getId()).getTags()
						.size());

	}

	@Test
	public void testRemoveTagToTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {

		TagBuilder tagBuilder = new TagBuilder.CreateBuilder(workspace.getId())
				.name("Newest Tag").build();
		
		assertNotNull(tagBuilder);
		Tag t = asana.TagOperations().createTag(tagBuilder);
		int previousSize = allTasks.get(0).getTags().size();
		taskOperator.addTagToTask(allTasks.get(0).getId(), t.getId());
		taskOperator.removeTagToTask(allTasks.get(0).getId(), t.getId());
		assertEquals(previousSize,
				taskOperator.getTaskById(allTasks.get(0).getId()).getTags()
						.size());
	}

	@Test
	public void testAddFollowerToTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		boolean added = false;
		taskOperator.addFollowerToTask(task.getId(), followers.get(0).getId());
		for (User v : task.getFollowers()) {
			if (v.getId().equals(followers.get(0).getId()))
				added = true;
		}
		assertTrue(added);
	}

	@Test
	public void testRemoveFollowerToTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		boolean removed = true;
		taskOperator.removeFollowerFromTask(task.getId(), followers.get(0)
				.getId());
		for (User v : task.getFollowers()) {
			if (v.getId().equals(followers.get(0).getId()))
				removed = false;
		}
		assertTrue(removed);
	}

	@Test
	public void testGetTaskById() {
		Task t = taskOperator.getTaskById(task.getId());
		assertNotNull(t);
	}

	@Test
	public void testGetAllTasksInWorkspace() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		List<Task> tasks = taskOperator
				.getAllTasksOfAllUsersInWorkspace(workspace.getId());
		assertNotNull(tasks);
	}

	@Test
	public void testGetAllTasksInWorkspaceForUserById() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		List<Task> tasks = taskOperator.getAllTasksInWorkspaceForUserById(
				workspace.getId(), followers.get(0).getId());
		assertNotNull(tasks);
	}

	@Test
	public void testGetAllTasksInWorkspaceAssignedToMe() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		List<Task> tasks = taskOperator
				.getAllTasksInWorkspaceAssignedToMe(workspace.getId());
		assertNotNull(tasks);

	}

	@Test
	public void testGetTask() {
		Task t = taskOperator.getTask(task);
		assertNotNull(t);
	}

	@Test
	public void testDeleteTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		Task t = task;
		taskOperator.deleteTask(t.getId());
		Boolean deleted = true;
		for (Task ta : taskOperator.getAllTasksOfAllUsersInWorkspace(workspace
				.getId())) {
			if (ta.getId().equals(t.getId()))
				deleted = false;
		}
		assertTrue(deleted);
	}

	@Test
	public void testGetAllProjectsForTask() {
		List<Project> p = taskOperator.getAllProjectsForTask(task.getId());
		assertNotNull(p);

	}

	@Test
	public void testGetSubTasks() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		TaskBuilder createTask = new TaskBuilder.CreateSubTaskBuilder(
				task.getId()).addFollowersSet(new HashSet<User>(followers))
				.assignee(owner.getId()).name("Test Task").notes("Test Notesz")
				.addNewTagsByCommaSeparatedNames("Tag1,Tag2,Tag3").build();
		Task t = taskOperator.createTask(createTask);
		List<Task> subTasks = taskOperator.getSubTasks(t.getId());
		assertNotNull(subTasks);
	}

	@Test
	public void testSetParentForTask() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		Task t = taskOperator.setParentForTask(allTasks.get(1).getId(),
				task.getId());
		assertEquals(task.getId(),taskOperator.getTaskById(allTasks.get(1).getId()).getParent().getId());
	}

	@Test
	public void testGetTaskForProject() throws RateLimitEnforcedException, InvalidRequestException, NoAuthorizationException, ForbiddenException, NotFoundException, IOException {
		assertNotNull(taskOperator.getTaskForProject(projects.get(0).getId()));
	}

}

Groovy-Asana-Api
================
<b>INFORMATION</b><br/>
Groovy-Asana-Api is a  Api written in  Java & Groovy. You can find the .Jar file in <b>dist</b> directory. Groovy-Asana-Api does not have any external dependency.
You Can you this Jar with any JVM Languages by adding  Groovy-all-1.8.0.jar or higher into build path.

<b><Usage></b><br/>
<pre><code>
Asana asana = new Asana("API KEY");
</code></pre>
Asana has Following Operations</br>
<ul>
<li>Task Operations </li>
<li>Project Operations </li>
<li>Tag Operations </li>
<li>Stories Operations </li>
<li>User Operations </li>
<li>Workspace Operations </li>
</ul>

<b>TASK OPERATIONS</b><br/>
Create Task
<pre><code>
TaskBuilder createTask = new TaskBuilder.CreateBuilder("Workspace_id")
			.assignee("USER_ID")
                        .name("Test Task")
                        .notes("Test Notesz")
                        .addNewTagsByCommaSeparatedNames("Tag1,Tag2,Tag3")//create Tag on fly
                        .build();
Task task = asana.TaskOperations().createTask(createTask);
</code></pre>

Update Task
<pre><code>
TaskBuilder taskBuilder = new TaskBuilder.UpdateTaskBuilder("TASK_ID)
					 .updateName(updateName)
					 .build();
task updatedTask = asana.TaskOperations().updateTask(taskBuilder);
</code></pre>

Get Task
<pre><code>
Task t = asana.TaskOperations().getTaskById("Task_ID");
</code></pre>
Delete Task
<pre><code>
 asana.TaskOperations().deleteTask(t.getId());
</code></pre>

<b>PROJECT OPERATIONS</b><br/>
Create Project
<pre><code>
ProjectBuilder projectCreator = new ProjectBuilder.CreateBuilder("WORKSPACE_ID")
						  .name("Just Fluf")
						  .notes("No Stuff")
						  .addFollower("5690619671404")
						  .build();
Project p =asana.ProjectOperations().createProject(projectCreator);
</code></pre>
update Project
<pre><code>
ProjectBuilder projectUpdater = new ProjectBuilder.UpdateBuilder("PROJECT_ID")
						  .name("Just Fluf Updated")
						  .archived(false)
						  .notes("No Stuff")
						  .addFollower("USER_ID")
						  .build();
asana.ProjectOperations().updateProject(projectUpdater);
</code></pre>
delete project
<pre><code>
asana.ProjectOperations().deleteProject("PROJECT_ID");
</code></pre>
get Project
<pre><code>
asana.ProjectOperations().getProjectById("PROJECT_ID");
</code></pre>

<b>TAG OPERATIONS</b><br/>
create Tag
<pre><code>
TagBuilder tagBuilder = new TagBuilder.CreateBuilder("WORKSPACE_ID")
				      .name("Tag 2")
				      .notes("Noitesz 2")
				      .build();
Tag tag = asana.TagOperations().createTag(tagBuilder);
</code></pre>
Update Tag
<pre><code>
TagBuilder tagBuilder = new TagBuilder.UpdateBuilder("WORKSPACE_ID")
				.name("updated Tag 2")
				.notes("Noitesz 2")
				.build();
Tag tag = asana.TagOperations().updateTag(tagBuilder);
</code></pre>
Get Tag
<pre><code>
asana.TagOperations().getTagById("WORKSPACE_ID");
</code></pre>
<b>STORIES OPERATIONS</b><br/>
Aviliable Opertions :
<ul>
<li>Get Stories For A Task</li>
<li>Get Stories For A Project</li>
<li>Get Story By Id</li>
<li>Comment On Task</li>
<li>Comment On Project</li>

</ul>

[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/7811493ab5ceb6d4a8214a8d20774c64 "githalytics.com")](http://githalytics.com/Vijay-Duke/Groovy-Asana-Api)

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
Create Task</br>
<pre><code>
TaskBuilder createTask = new TaskBuilder.CreateBuilder("Workspace_id")
				                                .assignee("USER_ID")
                                        .name("Test Task")
                                        .notes("Test Notesz")
                                        .addNewTagsByCommaSeparatedNames("Tag1,Tag2,Tag3")//create Tag on fly
                                        .build();
Task task = asana.TaskOperations().createTask(createTask);
</code></pre>

Update Task<br/>
<pre><code>
TaskBuilder taskBuilder = new TaskBuilder.UpdateTaskBuilder(
				task.getId()).updateName(updateName)
				.build();
task updatedTask = asana.TaskOperations().updateTask(taskBuilder);
</code></pre>

Get Task<br/>
<pre><code>
Task t = asana.TaskOperations().getTaskById("Task_ID");
</code></pre>
Delete Task<br/>
<pre><code>
 asana.TaskOperations().deleteTask(t.getId());
</code></pre>










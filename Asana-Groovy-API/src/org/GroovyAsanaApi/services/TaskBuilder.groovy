package org.GroovyAsanaApi.services;

import java.util.List;
import java.util.Set;

import org.GroovyAsanaApi.Domain.Assignee_status;
import org.GroovyAsanaApi.Domain.Followers;
import org.GroovyAsanaApi.Domain.Project
import org.GroovyAsanaApi.Domain.Task
import org.GroovyAsanaApi.Domain.User

public class TaskBuilder {

	private String workspaceId;
	private String taskId="print";
	private String openPostData;
	private String closePostData;
	private String postData;
	private String parent;
	private List<String> putDataList = new ArrayList<String>();
	private List<String> followerList = new ArrayList<String>();
	private List<String> addTaskToProjectList = new ArrayList<String>();
	private List<String> removeTaskFromProjectList = new ArrayList<String>();
	private List<String> addNewTags = new ArrayList<String>();
	private List<String> addExistingTags = new ArrayList<String>();
	private boolean isSubTask=false;
	private String parentOrSubtaskValue;


	private TaskBuilder(Builder b) {
		this.taskId = b.taskId;
		this.workspaceId = b.workspaceId
		this.parent = b.parent
		this.putDataList = b.putDataList;
		this.followerList = b.followerSet.asList();
		this.addTaskToProjectList = b.addTaskToProjectList;
		this.removeTaskFromProjectList = b.removeTaskFromProjectList;
		this.addNewTags = b.addNewTags;
		this.addExistingTags = b.addExistingTags;
		this.openPostData = b.openPostData;
		this.closePostData = b.closePostData;
		this.postData = b.postData;
		this.isSubTask = b.isSubTask;

		this.parentOrSubtaskValue = b.parentOrSubtaskValue;
	}

	public static class CreateBuilder extends TaskBuilder.Builder implements AttachProject{
		protected CreateBuilder(){}
		public CreateBuilder(String workspaceId) {
			putDataList.add(""" "workspace":${workspaceId}  """);
		}

		@Override
		public AttachProject attachTaskToProjectsByIdSet(Set<String> projectsIdSet) {
			projectSet.addAll(projectsIdSet);
			return this;
		}
		@Override
		public AttachProject attachTaskToProjectSet(Set<Project> projectsSet) {
			for(Project project : projectsSet)
			{
				projectSet.add(project);
			}
			return this;
		}

		@Override
		public AttachProject attachTaskToProjects(String projectId) {
			projectSet.add(projectId);
			return this;
		}

		public TaskBuilder build(){
			super.build()
		}

		public CreateBuilder assignee(String assigneeId){
			super.assignee(assigneeId)
		}

		public CreateBuilder name(String name){
			super.name(name)
		}

		public CreateBuilder notes(String notes){
			super.notes(notes)
		}

		public CreateBuilder completed(boolean status){
			super.completed(status)
		}

		public CreateBuilder due_on(String due_on){
			super.due_on(due_on)
		}

		public CreateBuilder assigneeStatus(Assignee_status assignee_status){
			super.assigneeStatus(assignee_status)
		}

		public CreateBuilder addFollower(String followerId){
			super.addFollower(followerId)
		}

		public CreateBuilder addFollowersIdSet(Set<String> followerSet){
			super.addFollowersIdSet(followerSet)
		}
		public CreateBuilder addFollowersSet(Set<User> followerSet){
			super.addFollowersSet(followerSet)
		}

		public CreateBuilder addNewTagsByCommaSeparatedNames(String tags) {
			super.addNewTagsByCommaSeparatedNames(tags)
		}

		public CreateBuilder addExistingTagsById(Set<String> tagsSet){
			super.addExistingTagsById(tagsSet)
		}
	}
	public static class CreateSubTaskBuilder extends CreateBuilder {


		public CreateSubTaskBuilder(String parentId) {
			putDataList.add(""" "parent":"${parentId}" """);
			isSubTask=true;
		}
	}
	public static class UpdateTaskBuilder extends TaskBuilder.Builder implements UpdateTask{


		public UpdateTaskBuilder(String taskId) {
			this.taskId = taskId
		}
		public UpdateTask removeTaskFromProjectsList(String projectId) {
			removeTaskFromProjectList.add(projectId);
			return this;
		}
		public UpdateTask addTaskToProjectsList(String projectId) {
			addTaskToProjectList.add(projectId)
			return this;
		}
		@Override
		public UpdateTaskBuilder updateAssignee(String assigneeId){
			return  (UpdateTaskBuilder)super.assignee(assigneeId);
		}
		public UpdateTaskBuilder updateName(String name) {
			return (UpdateTaskBuilder)super.name(name)
		}
		public UpdateTaskBuilder updateNotes(String notes){
			return (UpdateTaskBuilder) super.notes(notes)
		}
		public UpdateTaskBuilder updateCompleted(boolean status){
			return (UpdateTaskBuilder) super.completed(status)
		}
		public UpdateTaskBuilder updateDue_on(String due_on){
			return (UpdateTaskBuilder) super.due_on(due_on)
		}
		public UpdateTaskBuilder updateAssigneeStatus(Assignee_status assignee_status){
			return (UpdateTaskBuilder) super.assigneeStatus(assignee_status)
		}
		public UpdateTaskBuilder addFollower(String followerId){
			return (UpdateTaskBuilder) super.addFollower(followerId)
		}
		public UpdateTaskBuilder addFollowerSet(Set<User> followerSet){
			return (UpdateTaskBuilder) super.addFollowersSet(followerSet)
		}
		public UpdateTaskBuilder addFollowerIdSet(Set<String> followerSet){
			return (UpdateTaskBuilder) super.addFollowersSet(followerSet)
		}
	}

	public static class Builder{
		protected String workspaceId;
		protected boolean isSubTask=false;
		protected String parentOrSubtaskValue;
		protected String openPostData ="""{"data": { """
		protected String closePostData ="""}} """
		protected String postData="";
		protected List<String> putDataList = new ArrayList<String>();
		protected Set<String> followerSet = new HashSet<String>();
		protected Set<String> projectSet = new HashSet<String>();
		protected List<String> addTaskToProjectList = new ArrayList<String>();
		protected List<String> removeTaskFromProjectList = new ArrayList<String>();
		protected List<String> addNewTags = new ArrayList<String>();
		protected List<String> addExistingTags = new ArrayList<String>();
		protected String parent;
		protected String taskId;
		protected Map keyValues = new HashMap<String, String>();

		protected Builder(){
		}

		

		public TaskBuilder build() {

			keyValues.each{ key,val ->

				putDataList.add(val);
			}
			int i=0
			followerSet.each { it ->
				putDataList.add(""" "followers[${i}]":${it} """);
				i=i+1;
			}
			for(String projectId:projectSet) {
				addTaskToProjectList.add(projectId);
			}
			return new TaskBuilder(this);
		}







		protected TaskBuilder.Builder assignee(String assigneeId) {
			if(keyValues.containsKey("assignee"))
				return this
			keyValues.put("assignee", """ "assignee":"${assigneeId}" """)
			return this;
		}
		protected TaskBuilder.Builder name(String name) {
			if(keyValues.containsKey("name"))
				return this
			keyValues.put("name",  """ "name":"${name}" """)
			return this;
		}
		protected TaskBuilder.Builder notes(String notes) {
			if(keyValues.containsKey("notes"))
				return this
			keyValues.put("notes",  """ "notes":"${notes}" """)
			return this;
		}

		protected TaskBuilder.Builder completed(boolean status) {
			if(keyValues.containsKey("completed"))
				return this
			keyValues.put("completed",  """ "completed":"${status}" """)
			return this;
		}
		protected TaskBuilder.Builder due_on(String due_on) {
			if(keyValues.containsKey("due_on"))
				return this
			keyValues.put("due_on",  """ "due_on":"${due_on}" """)
			return this;
		}
		protected TaskBuilder.Builder assigneeStatus(Assignee_status assignee_status) {
			if(keyValues.containsKey("assignee_status"))
				return this
			keyValues.put("assignee_status", """ "assignee_status":"${assignee_status}" """)
			return this;
		}
		protected TaskBuilder.Builder addFollower(String followerId) {
			followerSet.add(followerId);
			return this;
		}
		protected TaskBuilder.Builder addFollowersIdSet(Set<String> followerSet) {
			followerSet.addAll(followerSet);
			return this;
		}
		protected TaskBuilder.Builder addFollowersSet(Set<User> followersSet) {
			for(User u : followersSet)
			{
				followerSet.add(u.getId());
			}
			return this;
		}
		protected TaskBuilder.Builder addNewTagsByCommaSeparatedNames(String CommaSeparatedNames) {
			addNewTags.addAll(CommaSeparatedNames.split(","));
			return this;
		}
		protected TaskBuilder.Builder addExistingTagsById(Set<String> tagsSet) {
			addExistingTags.addAll(tagsSet);
		}
	}

	String getUpdateTaskToPutFormat() {
		postData += openPostData
		/*if(!isSubTask) {
		 postData += parentOrSubtaskValue+","
		 }*/
		String putData =""
		for (String s : putDataList) {
			putData += s + ",";
		}

		putData = addJsonProjectToTask(putData)
		postData += closePostData
		return postData
	}

	private String addJsonProjectToTask(String putData) {
		if(addTaskToProjectList.size()>0) {
			putData += """ "projects":[ """
			for(String s : addTaskToProjectList) {
				putData += s + ",";
			}
			putData = putData.substring(0,putData.lastIndexOf(","));
			putData += """ ], """
		}
		putData= putData.substring(0,putData.lastIndexOf(","));
		postData += putData
		return putData
	}
	List<String> getAddProjectList() {
		return addTaskToProjectList
	}
	public List<String> getRemoveProjectList() {
		return removeTaskFromProjectList
	}
	public List<String> getNewTags() {
		return addNewTags
	}
	public List<String> getExistingTags() {
		return addExistingTags
	}
}
package com.codeclan.projectmanager12;

import java.util.ArrayList;

/**
 * Created by user on 22/03/2017.
 */

public class Project {
    private String projectName;
    private ArrayList<Task> taskList;

    public Project(String projectName) {
        this.projectName = projectName;
        this.taskList = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }


}

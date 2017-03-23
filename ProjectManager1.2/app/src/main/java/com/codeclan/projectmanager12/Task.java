package com.codeclan.projectmanager12;

/**
 * Created by user on 22/03/2017.
 */

public class Task {


    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void getTaskName(String taskName) {
        this.taskName = taskName;
    }
}

package com.codeclan.projectmanager12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskListActivity extends AppCompatActivity {

    Button backToProjectListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        backToProjectListButton = (Button)findViewById(R.id.buttonBackToProjectList);
    }

    public void clickedBackToProjectList(View view) {
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }
}

package com.codeclan.projectmanager12;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> taskAdapter;
    private ListView listViewTasks;

    Button backToProjectListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        backToProjectListButton = (Button)findViewById(R.id.buttonBackToProjectList);

        listViewTasks = (ListView)findViewById(R.id.listViewTasks);
        tasks = new ArrayList<String>();
//        readTasks();
        taskAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);

        listViewTasks.setAdapter(taskAdapter);
        listViewDeleteLongListener();
    }

    public void clickedBackToProjectList(View view) {
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    public void clickedNewTask(View view) {
        EditText editNewTask = (EditText)findViewById(R.id.editNewTask);
        String taskText = editNewTask.getText().toString();

        taskAdapter.add(taskText);
        editNewTask.setText("");
//        writeTasks();
    }


    public void listViewDeleteLongListener() {
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(TaskListActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + tasks.get(pos) + " task?");
                final int taskToRemove = pos;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tasks.remove(taskToRemove);
                        taskAdapter.notifyDataSetChanged(); // refreshes the view list
                    }});
                adb.show();
//                writeTasks();
                return true;
            }
        });
    }





//    private void readTasks() {
//        File filesDir = getFilesDir();
//        File taskFile = new File(filesDir, "task.txt");
//        try {
//            tasks = new ArrayList<String>(FileUtils.readLines(taskFile));
//        } catch (IOException e) {
//            tasks = new ArrayList<String>();
//        }
//    }
//
//    private void writeTasks() {
//        File filesDir = getFilesDir();
//        File taskFile = new File(filesDir, "task.txt");
//        try {
//            FileUtils.writeLines(taskFile, tasks);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}

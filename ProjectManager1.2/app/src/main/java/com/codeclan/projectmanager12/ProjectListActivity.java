package com.codeclan.projectmanager12;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity {

    private ArrayList<String> projects;
    private ArrayAdapter<String> projectAdapter;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        listViewItems = (ListView)findViewById(R.id.listViewItems);
        projects = new ArrayList<String>();
        projectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects);


        listViewItems.setAdapter(projectAdapter);
        projects.add("Ruby/Sql");
        projects.add("Android");
        listViewDeleteLongListener();
        listViewEditListener();
    }

    public void clickedAddItem(View view) {
        EditText editNewProject = (EditText)findViewById(R.id.editNewProject);
        String projectText = editNewProject.getText().toString();

        projectAdapter.add(projectText);
        editNewProject.setText("");
    }

    public void listViewDeleteLongListener() {
        listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ProjectListActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + projects.get(pos) + " project ?");
                final int projectToRemove = pos;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        projects.remove(projectToRemove);
                        projectAdapter.notifyDataSetChanged(); // refreshes the view list
                    }});
                adb.show();
                return true;
            }
        });
    }

    public void listViewEditListener() {
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                Intent intent = new Intent(ProjectListActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        }
        );}
    }
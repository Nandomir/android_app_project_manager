package com.codeclan.projectmanager12;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity {

    public static final String PROJECTS = "Projects";

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
        readProjects();

        listViewItems.setAdapter(projectAdapter);

        listViewDeleteLongListener();
        listViewEditListener();
    }

    public void clickedAddItem(View view) {
        EditText editNewProject = (EditText)findViewById(R.id.editNewProject);
        String projectText = editNewProject.getText().toString();

        Project newProjectObject = new Project(projectText);

        projectAdapter.add(projectText);
        editNewProject.setText("");
        writeProjects(newProjectObject);
    }

    public void listViewDeleteLongListener() {
        listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                AlertDialog.Builder messageBox = new AlertDialog.Builder(ProjectListActivity.this);
                messageBox.setTitle("Delete?");
                messageBox.setMessage("Are you sure you want to delete " + projects.get(pos) + " project ?");
                final int projectToRemove = pos;
                messageBox.setNegativeButton("Cancel", null);
                messageBox.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Project> existingArray = readProjects();
                        existingArray.remove(projectToRemove);
                        updateProjects(existingArray);
                        projectAdapter.notifyDataSetChanged(); // refreshes the view list
                    }});
                messageBox.show();
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



    private void writeProjects(Project newProjectObject) {
        ArrayList<Project> sharedPrefsRetrieval = readProjects();  // getting already existing array

        SharedPreferences sharedPrefs = getSharedPreferences(PROJECTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Gson gson = new Gson();
        sharedPrefsRetrieval.add(newProjectObject);

        editor.putString("project list", gson.toJson(sharedPrefsRetrieval));
        editor.apply();
    }

    private void updateProjects(Project newProjectObject) {
        ArrayList<Project> sharedPrefsRetrieval = readProjects();

        SharedPreferences sharedPrefs = getSharedPreferences(PROJECTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Gson gson = new Gson();
        sharedPrefsRetrieval.remove(newProjectObject);

        editor.putString("updated project list", gson.toJson(sharedPrefsRetrieval));
        editor.apply();
    }


    public ArrayList<Project> readProjects() {
        ArrayList<Project> emptyProjectArrayList = new ArrayList<Project>();

        Gson gson = new Gson();

        String converted = gson.toJson(emptyProjectArrayList);

        SharedPreferences sharedPref = getSharedPreferences(PROJECTS, Context.MODE_PRIVATE);

        String projects = sharedPref.getString("project list", converted);
        Log.d("Is there projects?", projects);

        TypeToken<ArrayList<Project>> projectList = new TypeToken<ArrayList<Project>>(){};

        ArrayList<Project> populatedProjectList = gson.fromJson(projects, projectList.getType());


        for(Project p : populatedProjectList){
            projectAdapter.add(p.getProjectName());
        }


        return populatedProjectList;

    }



//    OLD METHODS USED TO PERSIST DATA TO A TXT FILE

//    private void readProjects() {
//        File filesDir = getFilesDir();
//        File projectFile = new File(filesDir, "project.txt");
//        try {
//            projects = new ArrayList<String>(FileUtils.readLines(projectFile));
//        } catch (IOException e) {
//            projects = new ArrayList<String>();
//        }
//    }
//
//    private void writeProjects() {
//        File filesDir = getFilesDir();
//        File projectFile = new File(filesDir, "project.txt");
//        try {
//            FileUtils.writeLines(projectFile, projects);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
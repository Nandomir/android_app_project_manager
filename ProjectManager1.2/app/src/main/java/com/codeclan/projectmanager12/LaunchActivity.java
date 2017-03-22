package com.codeclan.projectmanager12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        startButton = (Button)findViewById(R.id.startButton);

    }

    public void startButtonClicked(View view){
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }
}

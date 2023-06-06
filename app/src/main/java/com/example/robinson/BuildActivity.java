package com.example.robinson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


public class BuildActivity extends AppCompatActivity {

    boolean b = false;
    int workAmplifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workAmplifier = Integer.parseInt(getIntent().getStringExtra("work"));
        BuildView buildView = new BuildView(this, workAmplifier, this);
        setContentView(buildView);
    }

}
package com.example.robinson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class BuildActivity extends AppCompatActivity {

    boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BuildView(this));
    }

}
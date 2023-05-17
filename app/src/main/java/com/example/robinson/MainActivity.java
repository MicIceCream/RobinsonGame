package com.example.robinson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnStart;

    EditText etName;

    Spinner spCharacters;

    Characters characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        etName = findViewById(R.id.etName);

//        spCharacters = findViewById(R.id.spCharacters);
//        ArrayList<String> characterList = new ArrayList<>();
//        for (int i = 0; i < characters.character.length; i++) {
//            characterList.add(characters.getCharacter(i).name);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, characterList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spCharacters.setAdapter(adapter);
        
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().length() <= 16) {
                    Intent i = new Intent(MainActivity.this, GameActivity.class);
                    i.putExtra("name", etName.getText().toString());
                    startActivity(i);
                } else
                    Toast.makeText(MainActivity.this, "Слишком большое имя. Не более 16 символов", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
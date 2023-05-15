package com.example.robinson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Button btnAct1;
    Button btnAct2;
    Button btnAct3;
    Button btnEndGame;
    Button btnSkip;

    TextView tvName;
    TextView tvStage;
    TextView tvContain;
    TextView tvFood;
    TextView tvWater;
    TextView tvMaterials;

    Player player;
    Day day = new Day();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = new Player(getIntent().getStringExtra("name"));

        tvName = findViewById(R.id.tvName);
        tvContain = findViewById(R.id.tvContain);
        tvFood = findViewById(R.id.tvFood);
        tvWater = findViewById(R.id.tvWater);
        tvMaterials = findViewById(R.id.tvMaterials);
        tvStage = findViewById(R.id.tvStage);

        btnAct1 = findViewById(R.id.btnAct1);
        btnAct2 = findViewById(R.id.btnAct2);
        btnAct3 = findViewById(R.id.btnAct3);


        tvStage.setText("Утро");
        tvFood.setText("Еда: " + player.getFood());
        tvWater.setText("Вода: " + player.getWater());
        tvMaterials.setText("Материалы: " + player.materials);

        tvName.setText("   " + player.name);

        btnEndGame = findViewById(R.id.btnEndGame);
        btnSkip = findViewById(R.id.btnSkip);
        btnEndGame.setEnabled(false);

        day.nextStage(player);
        checkActions();
        btnAct1.setText(day.action1.name);
        btnAct2.setText(day.action2.name);
        btnAct3.setText(day.action3.name);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupOnClickListener(day.skipAction);
            }
        });

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupOnClickListener(day.action1);
            }
        });
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupOnClickListener(day.action2);
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupOnClickListener(day.action3);
            }
        });
    }

    public void setupOnClickListener(Action action) {


        player.chooseAction(action);

        tvContain.setText(action.contain);
        day.nextStage(player);

        if (day.stage == 0)
            Toast.makeText(GameActivity.this, "День " + player.daySurvived, Toast.LENGTH_SHORT).show();
        btnAct1.setText(day.action1.name);
        btnAct2.setText(day.action2.name);
        btnAct3.setText(day.action3.name);

        tvStage.setText(day.stage == 0 ? "Утро" : "Вечер");

        tvFood.setText("Еда: " + player.getFood());
        tvWater.setText("Вода: " + player.getWater());
        tvMaterials.setText("Материалы: " + player.materials);

        if (action.name.equals("Построить лодку")) {
            endGame();
            Toast.makeText(this, "Игра завершена! Вы уплыли с острова!", Toast.LENGTH_LONG).show();
        } else
        if (player.food <= 0 || player.water <= 0) {
        endGame();
        Toast.makeText(this, "Игра завершена. Вы умерли(", Toast.LENGTH_LONG).show();
        } else
            if (player.daySurvived > 60) {
                endGame();
                tvContain.setText("Просыпаясь в очередной унылый день, вы замечаете корабль вдалеке. Через пару часов вас подобрали спасатели");
                Toast.makeText(this, "Игра завершена. Вы дождались спасателей!", Toast.LENGTH_LONG).show();
            } else
            checkActions();
    }

    void endGame() {
            btnAct1.setEnabled(false);
            btnAct2.setEnabled(false);
            btnAct3.setEnabled(false);
            btnSkip.setEnabled(false);
            btnEndGame.setEnabled(true);
    }

    public void checkActions() {
            btnAct1.setEnabled(day.action1.changedMaterials >= 0 || player.materials + day.action1.changedMaterials >= 0);
            btnAct2.setEnabled(day.action2.changedMaterials >= 0 || player.materials + day.action2.changedMaterials >= 0);
            btnAct3.setEnabled(day.action3.changedMaterials >= 0 || player.materials + day.action3.changedMaterials >= 0);
    }

}
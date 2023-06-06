package com.example.robinson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends FragmentActivity implements PostMan {


    Button btnBio, btnAction;


    FragmentTransaction ft;
    FragmentManager fm;
    BioFragment bioFragment;
    ActionFragment actionFragment;
    LinearLayout lnMain;

    androidx.fragment.app.Fragment currentFragment;


    Player player;
    Day day = new Day();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bioFragment = new BioFragment();
        actionFragment = new ActionFragment();
        currentFragment = new Fragment();
        lnMain = findViewById(R.id.lnMain);

        fm = getSupportFragmentManager();

        player = new Player(getIntent().getStringExtra("name"), new Character("Глеб", "Программист", 23, 100, 1, 1, 1) );

        btnBio = findViewById(R.id.btnBio);
        btnAction = findViewById(R.id.btnAction);



//        btnMap = findViewById(R.id.btnMap);
//        btnAct1 = findViewById(R.id.btnAct1);
//        btnAct2 = findViewById(R.id.btnAct2);
//        btnAct3 = findViewById(R.id.btnAct3);

//        btnEndGame = findViewById(R.id.btnEndGame);
//        btnSkip = findViewById(R.id.btnSkip);
//        btnEndGame.setEnabled(false);
        day.nextStage(player);
//        Bundle bundle = new Bundle();
//        bundle.putString("act1", day.action1.name);
//        bundle.putString("act2", day.action2.name);
//        bundle.putString("act3", day.action3.name);
//        bundle.putString("cost1",getActionCost(day.action1));
//        bundle.putString("cost2",getActionCost(day.action2));
//        bundle.putString("cost3",getActionCost(day.action3));
//        replaceFragment(actionFragment, bundle);


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("act1", day.action1.name);
                b.putString("act2", day.action2.name);
                b.putString("act3", day.action3.name);
                b.putString("cost1",getActionCost(day.action1));
                b.putString("cost2",getActionCost(day.action2));
                b.putString("cost3",getActionCost(day.action3));
                b.putBoolean("act1Possible", isActionPossible(day.action1));
                b.putBoolean("act2Possible", isActionPossible(day.action2));
                b.putBoolean("act3Possible", isActionPossible(day.action3));
                replaceFragment(actionFragment, b);
            }
        });


        btnBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                replaceFragment(bioFragment);
                Bundle bundle = new Bundle();
                bundle.putString("name", player.character.name);
                bundle.putString("profession", player.character.profession);
                bundle.putString("age", player.character.age + "");
                bundle.putString("mood", player.currentMood.name);
                bundle.putString("food", player.food + "");
                bundle.putString("water", player.water + "");
                bundle.putString("materials", player.materials + "");
                bundle.putString("day", player.daySurvived + 1 + "");
                bundle.putString("stage", day.stage == 0 ? "Утро" : "Вечер");
                replaceFragment(bioFragment, bundle);
            }
        });



//        checkActions();
//        btnAct1.setText(day.action1.name);
//        btnAct2.setText(day.action2.name);
//        btnAct3.setText(day.action3.name);

//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setupOnClickListener(day.skipAction);
//            }
//        });

//        btnEndGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//
//        btnAct1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setupOnClickListener(day.action1);
//            }
//        });
//        btnAct2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setupOnClickListener(day.action2);
//            }
//        });
//        btnAct3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setupOnClickListener(day.action3);
//            }
//        });
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent j = new Intent(GameActivity.this, MainActivity.class);
//                j.putExtra("player", player.map.locations);
//            }
//        });
    }

    public void setupOnClickListener(Action action) {



        player.chooseAction(action);
//        tvContain.setText(action.contain);
        Intent j = new Intent(GameActivity.this, BuildActivity.class);
        j.putExtra("work", player.workAmplifier + "");
        startActivityForResult(j, 1);
        day.nextStage(player);
        Bundle b = new Bundle();
        b.putString("act1", day.action1.name);
        b.putString("act2", day.action2.name);
        b.putString("act3", day.action3.name);
        b.putString("cost1",getActionCost(day.action1));
        b.putString("cost2",getActionCost(day.action2));
        b.putString("cost3",getActionCost(day.action3));
        b.putBoolean("act1Possible", isActionPossible(day.action1));
        b.putBoolean("act2Possible", isActionPossible(day.action2));
        b.putBoolean("act3Possible", isActionPossible(day.action3));
        replaceFragment(actionFragment, b);
        if (day.stage == 0) {
//            Toast.makeText(GameActivity.this, "День " + player.daySurvived + 1, Toast.LENGTH_SHORT).show();
            lnMain.setBackground(getDrawable(R.drawable.sun_theme));
        } else {
            lnMain.setBackground(getDrawable(R.drawable.evening_theme));
        }

//        btnAct1.setText(day.action1.name);
//        btnAct2.setText(day.action2.name);
//        btnAct3.setText(day.action3.name);

//        tvStage.setText(day.stage == 0 ? "Утро" : "Вечер");


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
                Toast.makeText(this, "Игра завершена. Вы дождались спасателей!", Toast.LENGTH_LONG).show();
            } else
            checkActions();
    }

    void endGame() {
//            btnAct1.setEnabled(false);
//            btnAct2.setEnabled(false);
//            btnAct3.setEnabled(false);
//            btnSkip.setEnabled(false);
//            btnEndGame.setEnabled(true);
    }

    public void checkActions() {
//            btnAct1.setEnabled(day.action1.changedMaterials >= 0 || player.materials + day.action1.changedMaterials >= 0);
//            btnAct2.setEnabled(day.action2.changedMaterials >= 0 || player.materials + day.action2.changedMaterials >= 0);
//            btnAct3.setEnabled(day.action3.changedMaterials >= 0 || player.materials + day.action3.changedMaterials >= 0);
 }
    void replaceFragment(androidx.fragment.app.Fragment fragment, Bundle b) {
        if (currentFragment.equals(fragment)) {
            ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
            currentFragment = new Fragment();
        } else {
            currentFragment = fragment;
            fragment.setArguments(b);
            ft = fm.beginTransaction();
            ft.replace(R.id.lnFragment1, fragment);
            ft.commit();
        }

    }
    String getActionCost(Action action) {
        return "Еда: " + action.changedFood + " Вода: " + action.changedWater + " Материалы: " + action.changedMaterials;
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        switch (buttonIndex) {
            case 1:
                if (isActionPossible(day.action1))
                    setupOnClickListener(day.action1);

                break;
            case 2:
                if (isActionPossible(day.action2))
                setupOnClickListener(day.action2);
                break;
            case 3:
                if (isActionPossible(day.action3))
                setupOnClickListener(day.action3);
        }
    }
    boolean isActionPossible(Action action) {
        return (player.food > -action.changedFood && player.water > -action.changedWater && player.materials >= -action.changedMaterials);
    }
}
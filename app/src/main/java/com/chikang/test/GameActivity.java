package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];

    private int Score;
    private int Life = 3;

    private TextView txtViewScore;
    private TextView txtViewLife;

    private String goblin = "\uD83D\uDC7A"; //👺
    private String angel = "\uD83D\uDC7C";  //👼
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        txtViewLife = findViewById(R.id.text_view_life);
        txtViewScore = findViewById(R.id.text_view_score);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String btn_ID = "button_"+i+j;
                int res_ID = getResources().getIdentifier(btn_ID, "id", getPackageName());
                buttons[i][j] = findViewById(res_ID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Random();

        Button btn_Reset = findViewById(R.id.button_reset);
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onButtonClick(View view) {
        Intent home = new Intent(this, HomeActivity.class);

        startActivity(home);
    }

    @Override
    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("")) {
            return;
        }

        if (((Button) v).getText().toString().equals(goblin)) {
            Score++;

        }

        if (((Button) v).getText().toString().equals(angel)) {
            Score -= 10;
            Life--;
        }
        UpdateStat();
        Random();
    }

    public void UpdateStat(){
        txtViewScore.setText("Score: " + Score);
        txtViewLife.setText("Life: " + Life);
    }

    public void Random() {
        Random rand = new Random();
        int pop = rand.nextInt(10);
        int i = rand.nextInt(4);
        int j = rand.nextInt(4);
        if (pop >= 3) {
            buttons[i][j].setText(goblin); //Goblin
        }
        else {
            buttons[i][j].setText(angel);
        }
    }
}

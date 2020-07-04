package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];
    private long time[][] = new long[4][4];

    private int Score;
    private int Life = 3;

    private int SpawnTime = 2000; //in milliseconds
    private int AppearDuration = 5000; //in milliseconds

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
        handler.postDelayed(runnable, 1000);

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
            ((Button) v).setText("");
            Score++;

        }

        if (((Button) v).getText().toString().equals(angel)) {
            ((Button) v).setText("");
            Score -= 10;
            Life--;
        }
        UpdateStat();
    }

    public void UpdateStat(){
        txtViewScore.setText("Score: " + Score);
        if (Score % 10 == 0) {
            if (SpawnTime > 500) {
                SpawnTime -= 30;
            }
            if (AppearDuration > 500) {
                AppearDuration -= 100;
            }
        }
        txtViewLife.setText("Life: " + Life);
    }

    public void Random() {
        Random rand = new Random();
        int gobNum = rand.nextInt(6) + 1;
        int angNum = rand.nextInt(10);
        int i,j;
        for (int k = 0; k < gobNum; k++) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
            buttons[i][j].setText(goblin);
            time[i][j] = System.currentTimeMillis();
        }
        if (angNum > 7) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
            if (buttons[i][j].getText().toString().equals("")) {
                buttons[i][j].setText(angel);
                time[i][j] = System.currentTimeMillis();
            }
        }
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Random();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!(buttons[i][j].getText().toString().equals(""))) {
                        if ((time[i][j] + AppearDuration) < System.currentTimeMillis()) {
                            if (buttons[i][j].getText().toString().equals(goblin)) {
                                Score -= 10;
                                Life--;
                            } else {
                                Score++;
                            }
                            buttons[i][j].setText("");
                            UpdateStat();
                        }
                    }
                }
            }

            handler.postDelayed(this, SpawnTime);
        }
    };

}

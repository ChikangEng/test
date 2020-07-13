package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];
    private long time[][] = new long[4][4];

    private boolean ReturnHome = false;

    private int Score;
    private int Life = 3;

    private int SpawnTime = 2000; //in milliseconds
    private int AppearDuration = 4000; //in milliseconds

    private TextView txtViewScore;
    private TextView txtViewLife;
    private TextView txtViewSpawnTime;
    private TextView txtViewAppearDuration;

    private String goblin = "\uD83D\uDC7A"; //👺
    private String angel = "\uD83D\uDC7C";  //👼
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        txtViewLife = findViewById(R.id.text_view_life);
        txtViewScore = findViewById(R.id.text_view_score);
        txtViewSpawnTime = findViewById(R.id.text_view_spawnTime);
        txtViewAppearDuration = findViewById(R.id.text_view_appearDuration);
        txtViewSpawnTime.setText("Spawn Time: " + SpawnTime);
        txtViewAppearDuration.setText("Appear Duration: " + AppearDuration);

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
        btn_Reset.setVisibility(View.GONE);
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

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
                txtViewSpawnTime.setText("Spawn Time: " + SpawnTime);
                if (AppearDuration > 500) {
                    AppearDuration -= 100;
                    txtViewAppearDuration.setText("Appear Duration: " + AppearDuration);
                }
            }
        }
        txtViewLife.setText("Life: " + Life);
        if(Life <= 0) {
            txtViewLife.setText("Life: 0");
            EndGame();
        }
    }

    public void Random() {
        Random rand = new Random();
        int gobNum = rand.nextInt(6) + 1;
        int angNum = rand.nextInt(10);
        int i,j;
        for (int k = 0; k < gobNum; k++) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
            if (buttons[i][j].getText().toString().equals(goblin)) {
                Score--;
                Life--;
            }
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
                            Score --;
                            Life--;
                        } else {
                            Score+=10;
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

    public void onButtonClick(View view) {
        if(ReturnHome) {
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        }
        EndGame();
    }

    private void EndGame() {
        handler.removeCallbacksAndMessages(null);
        buttons[0][0].setText("G");
        buttons[0][1].setText("A");
        buttons[0][2].setText("M");
        buttons[0][3].setText("E");
        buttons[1][0].setText("O");
        buttons[1][1].setText("V");
        buttons[1][2].setText("E");
        buttons[1][3].setText("R");
        buttons[2][0].setText("");
        buttons[2][1].setText("");
        buttons[2][2].setText("");
        buttons[2][3].setText("");
        buttons[3][0].setText("");
        buttons[3][1].setText("");
        buttons[3][2].setText("");
        buttons[3][3].setText("");
        findViewById(R.id.button_reset).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.button_back)).setText("Back");
        ReturnHome = true;
    }

    private void loadLead(){

        //load data from url
        String url = "http://10.0.2.2/game/leaderboard.php";

        //Create a request
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Convert JSON string to array using GSON
                        Gson gson = new Gson();
                        Leaderboard[] lead = gson.fromJson(response, Leaderboard[].class);
                        Toast.makeText(GameActivity.this, ""+goblin, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GameActivity.this, "Something Error While Loading Data From Server", Toast.LENGTH_LONG).show();
                Log.d("gametest", "Load data error : " + error.getMessage());
            }
        });

        //Add request to Queue
        Volley.newRequestQueue(this).add(request);

    }
}

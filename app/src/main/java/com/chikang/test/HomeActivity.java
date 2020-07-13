package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
    }

    public void StartButtonClick(View view) {
        Intent game = new Intent(this, GameActivity.class);

        startActivity(game);
    }
    public void SettingButtonClick(View view) {
        Intent setting = new Intent(this, SettingActivity.class);

        startActivity(setting);
    }
    public void LeaderboardButtonClick(View view) {
        Intent lead = new Intent(this, LeaderboardActivity.class);

        startActivity(lead);
    }
    public void HistoryButtonClick(View view) {
        Intent hist = new Intent(this, HistoryActivity.class);

        startActivity(hist);
    }
}

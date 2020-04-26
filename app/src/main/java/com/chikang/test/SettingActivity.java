package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_activity);
    }

    public void onButtonClick(View view) {
        Intent game = new Intent(this, GameActivity.class);

        startActivity(game);
    }
}

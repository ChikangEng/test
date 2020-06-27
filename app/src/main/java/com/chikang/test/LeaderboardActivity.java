package com.chikang.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class LeaderboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leaderboard);

        // Make a reference to the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);

        // What do u need for RecyclerView? : LayoutManager and Adapter
        // Create and set a LayoutManager : LinearLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadLead();
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
                    // Create and set an adapter
                    LeaderboardAdapter adapter = new LeaderboardAdapter(lead);
                    recyclerView.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LeaderboardActivity.this, "Something Error While Loading Data From Server", Toast.LENGTH_LONG).show();
                    Log.d("gametest", "Load data error : " + error.getMessage());
                }
            });

        //Add request to Queue
        Volley.newRequestQueue(this).add(request);

    }

}

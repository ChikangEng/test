package com.chikang.test;

import android.os.Bundle;
import android.util.Log;
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

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadHist();
    }

    private void loadHist(){

        //load data from url
        String url = "https://my.api.mockaroo.com/history.json?key=7738e660";

        //Create a request
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Convert JSON string to array using GSON
                        Gson gson = new Gson();
                        History[] hist = gson.fromJson(response, History[].class);
                        // Create and set an adapter
                        HistoryAdapter adapter = new HistoryAdapter(hist);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HistoryActivity.this, "Something Error While Loading Data From Server", Toast.LENGTH_LONG).show();
                Log.d("gametest", "Load data error : " + error.getMessage());
            }
        });

        //Add request to Queue
        Volley.newRequestQueue(this).add(request);

    }
}

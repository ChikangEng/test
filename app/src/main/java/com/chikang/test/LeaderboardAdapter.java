package com.chikang.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {

    // Dataset
    private Leaderboard[] leaderboard;

    //Generated Constructor
    public LeaderboardAdapter(Leaderboard[] leaderboard) {
        this.leaderboard = leaderboard;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a new Layout to display the viewholder within another layout file
        //load layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_holder_leaderboard, parent, false);

        return new LeaderboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        Leaderboard lead = leaderboard[position];
        holder.bind(lead);
    }

    @Override
    public int getItemCount() {
        return leaderboard.length;
    }
}

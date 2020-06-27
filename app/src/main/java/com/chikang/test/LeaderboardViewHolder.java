package com.chikang.test;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUsername;
    private TextView txtScore;

    public LeaderboardViewHolder(@NonNull View itemView) {
        super(itemView);

        txtUsername = itemView.findViewById(R.id.txt_username);
        txtScore = itemView.findViewById(R.id.txt_score);
    }

    public void bind(Leaderboard lead) {
        txtUsername.setText(lead.getUsername());
        txtScore.setText(lead.getScore());
    }

}

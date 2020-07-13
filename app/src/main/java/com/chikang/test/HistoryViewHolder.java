package com.chikang.test;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUsername;
    private TextView txtScore;
    private TextView txtDate;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txtUsername = itemView.findViewById(R.id.txt_username);
        txtScore = itemView.findViewById(R.id.txt_score);
        txtDate = itemView.findViewById(R.id.txt_date);
    }

    public void bind(History hist) {
        txtUsername.setText("Username : " + hist.getUsername());
        txtScore.setText("Score : " + hist.getScore());
        txtDate.setText(hist.getPlayDate());
    }
}

package com.chikang.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    // Dataset
    private History[] history;

    //Generated Constructor
    public HistoryAdapter(History[] history) {
        this.history = history;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a new Layout to display the viewholder within another layout file
        //load layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_holder_history, parent, false);

        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History hist = history[position];
        holder.bind(hist);
    }

    @Override
    public int getItemCount() {
        return history.length;
    }

}

package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoriesViewHolder>{

    private List<Article> articleList;
    private Context context;
    private OnImageClickListener listener;

    public TopStoriesAdapter(List<Article> articleList, Context context, OnImageClickListener clickListener) {
        this.articleList = articleList;
        this.context = context;
        this.listener = clickListener;

    }

    @NonNull
    @Override
    public TopStoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.top_stories_layout, parent, false);
        return new TopStoriesAdapter.TopStoriesViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesViewHolder holder, int position) {

        holder.topStoryImageView.setImageResource(articleList.get(position).getImage());
    }

    @Override
    public int getItemCount() {

        return articleList.size();
    }

    public class TopStoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView topStoryImageView;
        public OnImageClickListener onImageClickListener;

        public TopStoriesViewHolder(@NonNull View itemView, OnImageClickListener onRowClickListener) {
            super(itemView);
            topStoryImageView = itemView.findViewById(R.id.topStoryImageView);
            this.onImageClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onImageClickListener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnImageClickListener {
        void onImageClick (int position);
    }



}

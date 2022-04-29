package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<Article> articleList;
    private Context context;
    private OnStoryClickListener listener;

    public NewsAdapter(List<Article> articleList, Context context, OnStoryClickListener clickListener) {
        this.articleList = articleList;
        this.context = context;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false);
        return new NewsAdapter.NewsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.newsImageView.setImageResource(articleList.get(position).getImage());
        holder.sourceTextView.setText(articleList.get(position).getSource());
        holder.headlineTextView.setText(articleList.get(position).getHeadline());

    }

    @Override
    public int getItemCount() {

        return articleList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView newsImageView;
        public TextView sourceTextView, headlineTextView;
        public OnStoryClickListener onStoryClickListener;

        public NewsViewHolder(@NonNull View itemView, OnStoryClickListener onStoryClickListener) {
            super(itemView);

            newsImageView = itemView.findViewById(R.id.articleImageView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            headlineTextView = itemView.findViewById(R.id.headlineTextView);
            this.onStoryClickListener = onStoryClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onStoryClickListener.onStoryClick(getAdapterPosition());

        }
    }

    public interface OnStoryClickListener {
        void onStoryClick (int position);
    }
}

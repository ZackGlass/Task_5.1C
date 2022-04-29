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

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.RelatedViewHolder> {

    private List<Article> articleList;
    private Context context;
    private OnRowClickListener listener;

    public RelatedAdapter(List<Article> articleList, Context context, OnRowClickListener clicklistener) {
        this.articleList = articleList;
        this.context = context;
        this.listener = clicklistener;
    }

    @NonNull
    @Override
    public RelatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.related_layout, parent, false);
        return new RelatedAdapter.RelatedViewHolder(itemView, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull RelatedViewHolder holder, int position) {
        holder.relatedImage.setImageResource(articleList.get(position).getImage());
        holder.relatedSource.setText(articleList.get(position).getSource());
        holder.relatedHeadline.setText(articleList.get(position).getHeadline());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public class RelatedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView relatedImage;
        public TextView relatedSource;
        public TextView relatedHeadline;
        public OnRowClickListener onRowClickListener;

        public RelatedViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);

            relatedImage = itemView.findViewById(R.id.relatedImageView);
            relatedSource = itemView.findViewById(R.id.relatedSourceTextView);
            relatedHeadline = itemView.findViewById(R.id.relatedHeadlineTextView);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onRowClick(getAdapterPosition());
        }
    }

    public interface OnRowClickListener {
        void onRowClick (int position);
    }

}

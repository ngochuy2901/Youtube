package com.example.youtube.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtube.Acivity.PlayVideoActivity;
import com.example.youtube.Model.Video;
import com.example.youtube.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Video> videoArrayList;

    public VideoAdapter(Context context, ArrayList<Video> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View videoView = inflater.inflate(R.layout.item_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(videoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Video video = videoArrayList.get(position);
        holder.titleTextView.setText(video.getTitle());
        holder.descriptionTextView.setText(video.getDescription());
        Glide.with(context).load(video.getUrl()).into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView thumbnailImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            thumbnailImageView = itemView.findViewById(R.id.video);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Video video = videoArrayList.get(position);
                    Intent intent = new Intent(context, PlayVideoActivity.class);
                    intent.putExtra("video", video);
                    context.startActivity(intent);
                }
            });
        }
    }
}

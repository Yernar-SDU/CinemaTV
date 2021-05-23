package com.example.bottomnavigationactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.network.NetworkService;

import java.util.List;

public class RecycleViewCardMovieAdapter extends RecyclerView.Adapter<RecycleViewCardMovieAdapter.MovieViewHolder> {
    private List<Movie> taskList;
    private Context mContext;


    public RecycleViewCardMovieAdapter(Context context,List<Movie> taskList){
        this.taskList = taskList;
        mContext = context;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_for_movie,parent,false);
        return new MovieViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (mContext != null){
            Glide.with(mContext)
                    .load(NetworkService.BASE_URL_IMAGES + taskList.get(position).getPoster_path())
                    .centerCrop()
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.placeholder_png)
                    .into(holder.movie_image);
        }
        String id = String.valueOf(taskList.get(position).getId());
        holder.id.setText(id);
        holder.movie_name.setText(taskList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView movie_name;
        public ImageView movie_image;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            movie_image = itemView.findViewById(R.id.movie_image);
            movie_name = itemView.findViewById(R.id.movie_name);
        }
    }


}

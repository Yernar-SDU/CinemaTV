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
import com.example.bottomnavigationactivity.model.Person;
import com.example.bottomnavigationactivity.network.NetworkService;

import java.util.List;

public class RecycleViewCardActorAdapter extends RecyclerView.Adapter<RecycleViewCardActorAdapter.ActorViewHolder> {
    private List<Person> taskList;
    private final Context mContext;


    public RecycleViewCardActorAdapter(Context context,List<Person> taskList){
        this.taskList = taskList;
        mContext = context;
    }
    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_for_actor,parent,false);
        return new ActorViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Glide.with(mContext)
                .load(NetworkService.BASE_URL_IMAGES + taskList.get(position).getProfile_path())
                .centerCrop()
                .placeholder(R.drawable.actor_placeholder)
                .into(holder.actor_image);
        String id = String.valueOf(taskList.get(position).getId());
        holder.id.setText(id);
        holder.actor_name.setText(taskList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public static class ActorViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView actor_name;
        public ImageView actor_image;
        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            actor_image = itemView.findViewById(R.id.actor_image);
            actor_name = itemView.findViewById(R.id.actor_name);
        }
    }


}

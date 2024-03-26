package com.example.netflix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private List<SeriesModel> modelList;

    public SeriesAdapter(List<SeriesModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,
                parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(modelList.get(position).getStitle());
        Glide.with(holder.imageView.getContext()).load(modelList
                .get(position).getSthumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendDataToDeTailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDeTailsActivity.putExtra("title", modelList.get(position).getStitle());
                sendDataToDeTailsActivity.putExtra("slink", modelList.get(position).getSlink());
                sendDataToDeTailsActivity.putExtra("cover", modelList.get(position).getScover());
                sendDataToDeTailsActivity.putExtra("thumb", modelList.get(position).getSthumb());
                sendDataToDeTailsActivity.putExtra("desc", modelList.get(position).getSdesc());
                sendDataToDeTailsActivity.putExtra("cast", modelList.get(position).getScast());
                sendDataToDeTailsActivity.putExtra("link", modelList.get(position).getTlink());
                Log.d("Bug",modelList.get(position).getTlink());


                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.imageView,
                                "imageMain");

                holder.itemView.getContext().startActivity(sendDataToDeTailsActivity,optionsCompat.toBundle());

    }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

package com.example.netflix;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;
    private List<DataModel> dataModels = new ArrayList<>();

    public SliderAdapter(Context context){
        this.context = context;
    }

    public void renewItems(List<DataModel> dataModels){
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.dataModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        String title = dataModels.get(position).getTtitle();
        viewHolder.trailer_title.setText(title);
        Log.d("Title Debug", "Title at position " + position + ": " + title);
        Glide.with(viewHolder.itemView).load(dataModels.get(position).getTurl()).into(viewHolder.slider_image);

        viewHolder.play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trailer_video = new Intent(context, PlayerActivity.class);
                trailer_video.putExtra("video", dataModels.get(position).getTvideo());
                trailer_video.putExtra("title", dataModels.get(position).getTtitle());
                v.getContext().startActivity(trailer_video);
            }
        });
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView slider_image;
        TextView trailer_title;
        FloatingActionButton play_button;

        public MyViewHolder(View itemView) {
            super(itemView);
            slider_image = itemView.findViewById(R.id.image_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
            play_button = itemView.findViewById(R.id.floatingActionButton);
        }
    }
}

package com.example.admin.xmltest.Comic;

/**
 * Created by HTML5 on 20/11/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Truyen;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HTML5 on 20/11/2017.
 */

public class ComicProfileAdapter extends RecyclerView.Adapter<ComicProfileAdapter.DataViewHolder>{
    private List<Truyen> truyens;
    private Context mContext;

    public ComicProfileAdapter(Context mContext, List<Truyen> truyens){
        this.truyens = truyens;
        this.mContext = mContext;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameItem;
        ImageView imgThumb;
        CardView cardView;;
        public  DataViewHolder(View itemView){
            super(itemView);
            itemView.setClickable(true);
            tvNameItem = (TextView)itemView.findViewById(R.id.tvNameComicItem);
            imgThumb = (ImageView)itemView.findViewById(R.id.imgThumbComicItem);
            cardView = (CardView)itemView.findViewById(R.id.cardComic);
        }
    }

    @Override
    public ComicProfileAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_truyentranh, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComicProfileAdapter.DataViewHolder holder, final int position) {
        final Truyen truyen = truyens.get(position);
        //ten truyen
        String name = truyen.getName();
        //rut gon ten truyen
        if(name.length() >= 10){
            name = name.copyValueOf(name.toCharArray(), 0, 10) + "...";
        }
        //set du lieu cho item
        holder.tvNameItem.setText(name);
        Picasso.with(this.mContext).load(truyen.getThumbnail()).into(holder.imgThumb);
        //bat su kien khi chon truyen
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ComicProfile.class);
                intent.putExtra("TRUYEN", truyens.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return truyens.size();
    }

}

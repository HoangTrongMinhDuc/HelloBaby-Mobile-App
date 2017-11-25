package com.example.admin.xmltest.Comic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Truyen;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HTML5 on 20/11/2017.
 */

public class ViewAllAdapter extends ArrayAdapter<Truyen> {
    private Context mContext;
    private int resource;
    private static List<Truyen> mTruyen;

    public  ViewAllAdapter(Context mContext, int resource, List<Truyen> mTruyen){
        super(mContext, resource, mTruyen);
        this.mContext = mContext;
        this.resource = resource;
        this.mTruyen = mTruyen;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_truyentranh, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvNameComicItem);
            viewHolder.imgThumb = (ImageView)convertView.findViewById(R.id.imgThumbComicItem);
            viewHolder.cardView = (CardView)convertView.findViewById(R.id.cardComic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Truyen truyen = mTruyen.get(position);
        //lay ten truyen
        String name = truyen.getName();
        //rut gon ten truyen
        if(name.length() >= 10){
            name = name.copyValueOf(name.toCharArray(), 0, 10) + "...";
        }
        //set data cho view
        viewHolder.tvName.setText(name);
        Picasso.with(this.mContext).load(truyen.getThumbnail()).into(viewHolder.imgThumb);
        //bat su kien nhan chon truyen
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ComicProfile.class);
                intent.putExtra("TRUYEN", truyen);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    public class ViewHolder {
        TextView tvName;
        ImageView imgThumb;
        CardView cardView;;
    }
}
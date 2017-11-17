package com.example.admin.xmltest.VideoYoutube;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HTML5 on 27/10/2017.
 */

public class GridVideoAdapter extends ArrayAdapter<Video> {
    private Context mContext;
    private int resource;
    private static List<Video> mVideo;

    public  GridVideoAdapter(Context mContext, int resource, List<Video> mVideo){
        super(mContext, resource, mVideo);
        this.mContext = mContext;
        this.resource = resource;
        this.mVideo = mVideo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvtitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.imgvideo1 = (ImageView) convertView.findViewById(R.id.imgvideo1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Video video = mVideo.get(position);
        String title = video.getTitle();
        if(video.getTitle().length()>=20){
            title = title.copyValueOf(title.toCharArray(),0,17) + "...";
        }
        viewHolder.tvtitle.setText(title);
        Picasso.with(mContext).load("https://i.ytimg.com/vi/"+video.getId()+"/hqdefault.jpg").into(viewHolder.imgvideo1);
        return convertView;
    }
    public class ViewHolder {
        TextView tvtitle;
        ImageView imgvideo1;
    }
}

package com.example.admin.xmltest.Comic;

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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HTML5 on 18/11/2017.
 */

public class ComicPlayerAdapter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    private List<String> arrJ;

    public ComicPlayerAdapter(Context context, int resource, List<String> arrJ){
        super(context, resource, arrJ);
        this.context = context;
        this.resource = resource;
        this.arrJ = arrJ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comic_pic, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgPic = (ImageView)convertView.findViewById(R.id.imgComicPic);
            viewHolder.tvPage = (TextView)convertView.findViewById(R.id.tvPage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String link = arrJ.get(position);
        int numPage = position +1;
        viewHolder.tvPage.setText("Trang "+numPage);
        Picasso.with(context).load(link).into(viewHolder.imgPic);
//        final ViewGroup.LayoutParams params = convertView.getLayoutParams();
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        convertView.setLayoutParams(params);


        return convertView;
    }
    public class ViewHolder {
        ImageView imgPic;
        TextView tvPage;
    }
}
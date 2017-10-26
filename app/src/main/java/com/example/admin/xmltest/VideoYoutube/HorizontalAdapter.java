package com.example.admin.xmltest.VideoYoutube;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.YoutubePlayer;
import com.example.admin.xmltest.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HTML5 on 25/10/2017.
 */

//Adapter con để hiển thị theo chiều ngang chứa các video
public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Video> mDataList;
    private int mRowIndex = -1;
    private Context mContext;

    //constructor
    public HorizontalAdapter(Context mContext) {
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    //hàm set dữ liệu cho adapter, nếu dữ liệu thay đổi thì cập nhật adapter
    public void setData(List<Video> data) {
            this.mDataList = data;
            notifyDataSetChanged();

    }
    //set index cho adapter ngang
    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    //lấy các view trong 1 item ra
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView text;
        private CardView itemVideo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgvideo1) ;
            text = (TextView) itemView.findViewById(R.id.tvTitle);
            itemVideo = (CardView) itemView.findViewById(R.id.itemVideo);
        }
    }

    //set item cho adapter ngang
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    //set text cho từng item và bắt các sự kiện khi nhấn vào item
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        final ItemViewHolder holder = (ItemViewHolder) rawHolder;
        //nếu title quá dài thì thu gọn
        final Video current =  mDataList.get(position);
        String title = current.getTitle();
        if(title.length()>=20)
            title =  title.copyValueOf(title.toCharArray(),0,16)+"...";
        holder.text.setText(title); //set title cho video

        holder.itemView.setTag(position);
        Picasso.with(mContext).load("https://i.ytimg.com/vi/"+current.getId()+"/hqdefault.jpg").into(holder.img);
        holder.itemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, YoutubePlayer.class);
                intent.putExtra("ID", current.getId());
                intent.putExtra("TITLE", current.getTitle());
                mContext.startActivity(intent);
                Toast.makeText(mContext, current.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
package com.example.admin.xmltest.VideoYoutube;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HTML5 on 25/10/2017.
 */

//Recycler Adapter mẹ cho các Horizontal Adapter

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.SimpleViewHolder> {

    private final Context mContext;
    private static List<Category> mCategory; //danh sach du liệu các thể loại
    private static RecyclerView horizontal_list; //recycler view để hiển thị dữ liệu theo chiều ngang của từng phần từ trong mCategory


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView category; //tiêu đề tên thể loại
        public final ImageView next; //nút để chuyển qua trang mới

        private HorizontalAdapter horizontalAdapter;

        public SimpleViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();

            category = (TextView) view.findViewById(R.id.tvCategory);
            next = (ImageView) view.findViewById(R.id.btnNext);
            horizontal_list = (RecyclerView) itemView.findViewById(R.id.horizontal_list);
            //cài đặt cho recycler ngang hiển thị theo chiều ngang
            horizontal_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            //set adapter cho recycler ngang
            horizontalAdapter = new HorizontalAdapter(context);
            horizontal_list.setAdapter(horizontalAdapter);
            horizontalAdapter.notifyDataSetChanged();
        }
    }
    //constructor
    public VerticalAdapter(Context mContext, List<Category> data) {
        this.mContext = mContext;
        if (data != null)
            mCategory = new ArrayList<>(data);
        else mCategory = new ArrayList<>();
    }
    public void addItems(List<Category> categories){
        mCategory.clear();
        mCategory.addAll(categories);
    }

    //set layout cho adapter
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        //set text cho layout ngang
        holder.category.setText(mCategory.get(position).getNameType()); //tên thể loại
        holder.horizontalAdapter.setData(mCategory.get(position).getVideos()); // set dữ liệu là danh sách video cho adapter ngang
        holder.horizontalAdapter.setRowIndex(position); //set index cho adapter ngang
        //bắt sự kiện khi chọn nút next, đưa ra màn hình mới
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mCategory.get(position).getNameType(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

}
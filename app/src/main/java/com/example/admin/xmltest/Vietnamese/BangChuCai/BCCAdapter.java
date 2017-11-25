package com.example.admin.xmltest.Vietnamese.BangChuCai;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.R;

import java.util.ArrayList;

/**
 * Created by PC on 11/25/2017.
 */

public class BCCAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<BCC> mList;

    public BCCAdapter(Context context, int layout, ArrayList<BCC> list){
        mContext = context;
        mLayout = layout;
        mList = list;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(mLayout, container, false);
        TextView tvalpha = (TextView)layout.findViewById(R.id.tvAlphaa);
        BCC pst = mList.get(position);
        tvalpha.setText(pst.getContent());
        container.addView(layout);
        return layout;
    }
}
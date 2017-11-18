package com.example.admin.xmltest.Education;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by USER on 11/18/2017.
 */

public class toandem110Adapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<ToanDemIdenX> mList;

    public toandem110Adapter(Context context, int layout, ArrayList<ToanDemIdenX> list){
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
        ImageView imglink = (ImageView) layout.findViewById(R.id.imgLink);
        TextView tvquestion = (TextView)layout.findViewById(R.id.tvQuestion);
        ToanDemIdenX pst = mList.get(position);
        Picasso.with(mContext).load(pst.getLink()).into(imglink);
        tvquestion.setText(pst.getQuestion());
        container.addView(layout);
        return layout;
    }
}

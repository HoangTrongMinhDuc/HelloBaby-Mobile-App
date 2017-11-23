package com.example.admin.xmltest.English;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.R;

import java.util.ArrayList;

/**
 * Created by USER on 11/23/2017.
 */

public class numberAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Number> mList;

    public numberAdapter(Context context, int layout, ArrayList<Number> list){
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
        TextView tv1 = (TextView)layout.findViewById(R.id.tvnum1);
        TextView tv2 = (TextView)layout.findViewById(R.id.tvnum2);
        TextView tv3 = (TextView)layout.findViewById(R.id.tvnum3);
        Number pst = mList.get(position);
        tv1.setText(pst.getNum());
        tv2.setText(pst.getContent());
        tv3.setText(pst.getSpell());
        container.addView(layout);
        return layout;
    }
}

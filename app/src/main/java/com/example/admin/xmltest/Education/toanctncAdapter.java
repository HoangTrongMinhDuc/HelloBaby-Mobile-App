package com.example.admin.xmltest.Education;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.R;

import java.util.ArrayList;

/**
 * Created by USER on 11/20/2017.
 */

public class toanctncAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Toanctncbe10> mList;

    public toanctncAdapter(Context context, int layout, ArrayList<Toanctncbe10> list){
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
        TextView tvquestion = (TextView)layout.findViewById(R.id.tvQuestionptoan);
        Toanctncbe10 pst = mList.get(position);
        tvquestion.setText(pst.getQuestion());
        container.addView(layout);
        return layout;
    }
}

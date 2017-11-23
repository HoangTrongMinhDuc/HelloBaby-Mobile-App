package com.example.admin.xmltest.English;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.Math.Toanctncbe10;
import com.example.admin.xmltest.R;

import java.util.ArrayList;

/**
 * Created by USER on 11/23/2017.
 */

public class alphabetAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Alphabet> mList;

    public alphabetAdapter(Context context, int layout, ArrayList<Alphabet> list){
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
        TextView tvalpha = (TextView)layout.findViewById(R.id.tvAlpha);
        TextView tvspell = (TextView)layout.findViewById(R.id.tvSpell);
        Alphabet pst = mList.get(position);
        tvalpha.setText(pst.getContent());
        tvspell.setText(pst.getSpell());
        container.addView(layout);
        return layout;
    }
}

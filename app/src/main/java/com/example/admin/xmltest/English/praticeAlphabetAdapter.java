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
 * Created by USER on 11/29/2017.
 */

public class praticeAlphabetAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Alphabet> mList;

    public praticeAlphabetAdapter(Context context, int layout, ArrayList<Alphabet> list){
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
        TextView tvspell = (TextView)layout.findViewById(R.id.tvSpellpra);
        Alphabet pst = mList.get(position);
        tvspell.setText(pst.getSpell());
        container.addView(layout);
        return layout;
    }
}

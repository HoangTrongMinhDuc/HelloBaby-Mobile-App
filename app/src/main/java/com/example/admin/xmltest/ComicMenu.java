package com.example.admin.xmltest;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.models.Chuong;
import com.example.admin.xmltest.models.Truyen;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by HTML5 on 21/09/2017.
 */

public class ComicMenu extends Fragment{
    private TextView tvVideoName;
    private DatabaseReference mDatabase;
    private Truyen truyen;
    private List<Chuong> chuongs;
    private List<String> links;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_comic, container, false);
        tvVideoName = (TextView)view.findViewById(R.id.tvVideoName);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        tvVideoName.setTypeface(typeface);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        List<String> links = new ArrayList<>();
//        List<Chuong> chuongs = new ArrayList<>();
//        String link = "http://1.bp.blogspot.com/-qE_CjIQgLKA/VdVrpqdKq_I/AAAAAAAGlHY/hx0nQC2JrEQ/s0/credit-dora.png?imgmax=3000";
//        links.add(link);
//        link = "http://1.bp.blogspot.com/-_qUbTY1Ht0E/VdVtht8a56I/AAAAAAAGlH4/Vz4SXGSXRis/s0/Doraemon 2015 (1).png?imgmax=3000";
//        links.add(link);
//        Chuong chuong = new Chuong("Ten Chuong truyen", 1,links);
//        chuongs.add(chuong);
//        Truyen truyen = new Truyen("Doraemon","Đây là phiên bản truyện tranh của Eiga Doraemon 2015, kể vệ nhóm bạn Nobita đóng phim làm siêu anh hùng. nhưng không ngờ lại được làm siêu anh hùng thật.", "truyen tranh",chuongs, "http://storage.fshare.vn/Test-vechai/1584-doraemon-nobita-va-nhung-hiep-si-khong-gian-.jpg");
//        mDatabase.child("Comic").child("Doraemon").setValue(truyen);
        return view;
    }
}

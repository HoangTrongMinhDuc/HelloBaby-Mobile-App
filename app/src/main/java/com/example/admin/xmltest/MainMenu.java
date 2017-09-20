package com.example.admin.xmltest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by HTML5 on 21/09/2017.
 */

public class MainMenu extends Fragment{
    Button btnGiaoDuc, btnTruyen, btnVideo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.man_hinh_chinh, container, false);
        addControls(view);
        addEvent(view);
        return view;
    }
    private void addControls(View view){
        btnGiaoDuc=(Button)view.findViewById(R.id.btnGiaoDuc);
        btnTruyen=(Button)view.findViewById(R.id.btnComic);
        btnVideo=(Button)view.findViewById(R.id.btnVideo);

    }
    private void addEvent(View view){
        btnGiaoDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft_add = fm.beginTransaction();
                ft_add.add(R.id.frameCN2,new EducationMenu());
                ft_add.commit();
            }
        });
        btnTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft_add = fm.beginTransaction();
                ft_add.add(R.id.frameCN2,new ComicMenu());
                ft_add.commit();
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft_add = fm.beginTransaction();
                ft_add.add(R.id.frameCN2,new VideoMenu());
                ft_add.commit();
            }
        });
    }
}

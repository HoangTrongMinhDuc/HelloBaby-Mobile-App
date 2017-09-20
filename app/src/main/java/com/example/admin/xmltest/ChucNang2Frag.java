package com.example.admin.xmltest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChucNang2Frag extends Fragment {
    Button btnGiaoDuc, btnTruyen, btnVideo;
    android.support.v4.app.FragmentManager fm;
    FrameLayout frameCN2;

    public ChucNang2Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chuc_nang2, container, false);
        getActivity().setTitle("CHƠI VÀ HỌC");
        addControls(view);
        initFragment(view);
        // Inflate the layout for this fragment
        return view;
    }
    private void addControls(View view){
        btnGiaoDuc=(Button)view.findViewById(R.id.btnGiaoDuc);
        btnTruyen=(Button)view.findViewById(R.id.btnComic);
        btnVideo=(Button)view.findViewById(R.id.btnVideo);
        frameCN2=(FrameLayout)view.findViewById(R.id.frameCN2);

    }
    private void initFragment(View view){
        MainMenu fragment2=new MainMenu();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameCN2,fragment2);
        fragmentTransaction.commit();
    }

}

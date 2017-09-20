package com.example.admin.xmltest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChucNang2Frag extends Fragment {
    Button btnGiaoDuc, btnTruyen, btnVideo;

    public ChucNang2Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chuc_nang2, container, false);
        getActivity().setTitle("CHƠI VÀ HỌC");
        addControls(view);
        addEvents(view);
        // Inflate the layout for this fragment
        return view;
    }
    private void addControls(View view){
        btnGiaoDuc=(Button)view.findViewById(R.id.btnGiaoDuc);
        btnTruyen=(Button)view.findViewById(R.id.btnComic);
        btnVideo=(Button)view.findViewById(R.id.btnVideo);

    }
    private void addEvents(View view){
        btnGiaoDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

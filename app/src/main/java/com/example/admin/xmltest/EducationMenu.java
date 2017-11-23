package com.example.admin.xmltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.xmltest.English.EngEduMainActivity;
import com.example.admin.xmltest.Math.EduMainActivity;


public class EducationMenu extends Fragment{
    Button btnTiengAnh, btnTienViet, btnToan;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.screen_edu, container, false);
        addControls(view);
        addEvent(view);
        return view;
    }

    private void addEvent(View view) {
        btnToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),EduMainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        btnTiengAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),EngEduMainActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void addControls(View view) {
        btnTiengAnh=(Button)view.findViewById(R.id.btnTiengAnh);
        btnTienViet=(Button)view.findViewById(R.id.btnTiengViet);
        btnToan=(Button)view.findViewById(R.id.btnToan);

    }


}

package com.example.admin.xmltest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.admin.xmltest.models.ButtonNumberCall;
import com.example.admin.xmltest.models.CallButtonAdapter;

import java.util.ArrayList;

/**
 * Created by admin on 11/23/2017.
 */

public class ChucNang1TestFrag extends Fragment {
    ArrayList<ButtonNumberCall> arrButton;
    GridView grButton;
    public ChucNang1TestFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chuc_nang1test, container, false);
        //Thay doi chu toolbar
        getActivity().setTitle("GỌI CHO NGƯỜI THÂN");

        init();
        setComponent(view);
        setEvents();


        return view;

    }


    private void init() {

        arrButton = new ArrayList<ButtonNumberCall>();

        arrButton.add(new ButtonNumberCall("owl","0987078077"));
        arrButton.add(new ButtonNumberCall("lion","0987078077"));
        arrButton.add(new ButtonNumberCall("water_seal","0987078077"));
        arrButton.add(new ButtonNumberCall("monkey","0987078077"));
    }
    private void setComponent(View view){
        grButton = view.findViewById(R.id.grButton);

    }
    private void setEvents() {
        CallButtonAdapter callButtonAdapter = new CallButtonAdapter(getContext(), R.layout.item_button_call, arrButton);
        grButton.setAdapter(callButtonAdapter);
        grButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),String.valueOf(i),Toast.LENGTH_LONG).show();
            }
        });

    }

}

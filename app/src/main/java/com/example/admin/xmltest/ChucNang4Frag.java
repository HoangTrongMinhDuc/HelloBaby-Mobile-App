package com.example.admin.xmltest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChucNang4Frag extends Fragment {


    public ChucNang4Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("CÀI ĐẶT");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chuc_nang4, container, false);
    }

}

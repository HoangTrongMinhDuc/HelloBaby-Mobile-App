package com.example.admin.xmltest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 9/13/2017.
 */

public class ChucNang3Frag extends Fragment {
    public ChucNang3Frag(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("QUẢN LÝ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chuc_nang3, container, false);
    }
}

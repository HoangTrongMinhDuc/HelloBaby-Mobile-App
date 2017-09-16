package com.example.admin.xmltest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by admin on 9/13/2017.
 */

public class ChucNang3Frag extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private View vView;
    private GoogleMap gMap;
    private ProgressDialog progressDialog;
    private Button btnFind;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vView=inflater.inflate(R.layout.fragment_chuc_nang3, container, false);
        getActivity().setTitle("QUẢN LÝ");
        progressDialog =new ProgressDialog(getContext());
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải bản đồ");
        progressDialog.show();


        btnFind= (Button) vView.findViewById(R.id.btnfind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdtcon="01647723485";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(sdtcon, null, "sms message", null, null);
            }
        });
        return vView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView= (MapView) vView.findViewById(R.id.map);
        mapView.onCreate(null);
        mapView.onResume();
        mapView.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng position=new LatLng(16.075798,108.1514833);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,18));
        gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                progressDialog.dismiss();
            }
        });
        gMap.addMarker(new MarkerOptions().position(position).title("Tao ở đây"));
    }
}

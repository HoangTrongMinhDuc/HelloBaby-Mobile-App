package com.example.admin.xmltest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by admin on 9/13/2017.
 */

public class ChucNang3Frag extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private View vView;
    private GoogleMap gMap;
//    private ProgressDialog progressDialog;
    private Dialog progressDialog;
    private Button btnFind;
    private String phoneNumber;



    BroadcastReceiver mReceiver;

    FirebaseAuth mAuth;
    DatabaseReference mData;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vView=inflater.inflate(R.layout.fragment_chuc_nang3, container, false);
        getActivity().setTitle("QUẢN LÝ");
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog =new ProgressDialog(getContext());
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.setTitle("Thông báo");
//        progressDialog.setMessage("Đang tải bản đồ");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);




        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("mother").child(mAuth.getCurrentUser().getUid()).child("sonPhone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phoneNumber = (String) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        btnFind= (Button) vView.findViewById(R.id.btnfind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String sdtcon="01647723485";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, "+hello123send sms message", null, null);
                Toast.makeText(getContext(),"Đang tìm...",Toast.LENGTH_LONG).show();
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
        LatLng position=new LatLng(16.0408051,108.2481322);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,35));
        gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                progressDialog.dismiss();
            }
        });
        gMap.addMarker(new MarkerOptions().position(position).title("Con ở đây"));
        Toast.makeText(getContext(),"mapready",Toast.LENGTH_LONG).show();
        IntentFilter intentFilter = new IntentFilter("SonReceiveAndSendBackService.intent.MAIN");

        mReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getContext(),"abc",Toast.LENGTH_LONG).show();
                String noidung = intent.getStringExtra("get_msg");
                                    Toast.makeText(getContext(),noidung,Toast.LENGTH_LONG).show();
                    if(noidung!="null" &&noidung.indexOf("+hello123receive")!=-1) {
                        noidung=noidung.replace("+hello123receive","");
                        String[] pos = noidung.split(":");
                        LatLng position = new LatLng(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]));
                        gMap.clear();
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 35));
                        gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                            @Override
                            public void onMapLoaded() {
                                progressDialog.dismiss();
                            }
                        });
                        gMap.addMarker(new MarkerOptions().position(position).title("Con ở đây"));
                    }
                    else
                        Toast.makeText(getContext(),"Không tìm thấy con ở đâu",Toast.LENGTH_LONG).show();
                }

//                Bundle bundle = intent.getExtras();
//                Object[] pdus = (Object[]) bundle.get("pdus");
//                for (int i = 0; i < pdus.length; i++) {
//                    SmsMessage smsMessage;
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        String format = bundle.getString("format");
//                        smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);
//                    } else {
//                        smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                    }
//                    String noidung = smsMessage.getMessageBody();
//                    Toast.makeText(getContext(),noidung,Toast.LENGTH_LONG).show();
//                    if(noidung!="null" &&noidung.indexOf("+hello123receive")!=-1) {
//                        noidung=noidung.replace("+hello123receive","");
//                        String[] pos = noidung.split(":");
//                        LatLng position = new LatLng(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]));
//                        gMap.clear();
//                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 35));
//                        gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
//                            @Override
//                            public void onMapLoaded() {
//                                progressDialog.dismiss();
//                            }
//                        });
//                        gMap.addMarker(new MarkerOptions().position(position).title("Con ở đây"));
//                    }
//                    else
//                        Toast.makeText(getContext(),"Không tìm thấy con ở đâu",Toast.LENGTH_LONG).show();
//                }

        };
        getContext().registerReceiver(mReceiver,intentFilter);
    }
}

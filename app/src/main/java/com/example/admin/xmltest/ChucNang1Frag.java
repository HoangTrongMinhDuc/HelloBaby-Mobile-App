package com.example.admin.xmltest;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChucNang1Frag extends Fragment {

    ImageView imgNum1,imgNum2,imgNum3,imgNum4,imgNum5,imgNum6,imgNum7,imgNum8,imgNum9;
    EditText input;
    String txt="";

    public ChucNang1Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chuc_nang1, container, false);
        addControls(view);
        ///giaLapDuLieu();
        addEvents();

        //Thay doi chu toolbar
        getActivity().setTitle("GỌI CHO NGƯỜI THÂN");
        return view;

    }
    private void giaLapDuLieu() {
        SharedPreferences sP = this.getActivity().getSharedPreferences("phone_num_to_call", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString("phone_num_1", "0987078071");
        editor.putString("phone_num_2", "0987078072");
        editor.putString("phone_num_3", "0987078073");
        editor.putString("phone_num_4", "0987078074");
        editor.putString("phone_num_5", "0987078075");
        editor.putString("phone_num_6", "0987078076");
        editor.putString("phone_num_7", "0987078077");
        editor.putString("phone_num_8", "0987078078");
        editor.putString("phone_num_9", "0987078079");
        editor.commit();
    }
    //
    private void addEvents() {
        imgNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,1);

            }
        });
        imgNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,2);

            }
        });
        imgNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,3);

            }
        });
        imgNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,4);

            }
        });
        imgNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,5);

            }
        });
        imgNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,6);

            }
        });
        imgNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,7);

            }
        });
        imgNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,8);

            }
        });
        imgNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyGoi(view,9);

            }
        });

        imgNum1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,1);
                return false;
            }
        });
        imgNum2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,2);
                return false;
            }
        });
        imgNum3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,3);
                return false;
            }
        });
        imgNum4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,4);
                return false;
            }
        });
        imgNum5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,5);
                return false;
            }
        });
        imgNum6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,6);
                return false;
            }
        });
        imgNum7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,7);
                return false;
            }
        });
        imgNum8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,8);
                return false;
            }
        });
        imgNum9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xuLyNhapSo(v,0);
                return false;
            }
        });
    }

    private void xuLyNhapSo(View v, final int i)
    {
        AlertDialog.Builder buider=new AlertDialog.Builder(getActivity());
        buider.setTitle("Nhập số điện thoại");
        buider.setIcon(R.mipmap.icon_running_rabbit);
        EditText editText=new EditText(getActivity());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        input=editText;
        buider.setView(input);

        buider.setPositiveButton("Nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt=input.getText().toString();
                //Lưu phone vào trong SharedReference
                savePhone(txt,i);
                //Toast.makeText(getActivity(),txt,Toast.LENGTH_LONG).show();
            }
        });
        buider.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog ad=buider.create();
        ad.show();

    }

    private void savePhone(String txt, int i) {
        SharedPreferences sP = this.getActivity().getSharedPreferences("phone_num_to_call", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();


        switch (i)
        {
            case 1:
                Toast.makeText(getActivity(),txt,Toast.LENGTH_LONG).show();
                editor.putString("phone_num_1", txt );
                break;
            case 2:
                editor.putString("phone_num_2", txt);
                break;
            case 3:
                editor.putString("phone_num_3", txt);
                break;
            case 4:
                editor.putString("phone_num_4", txt);
                break;
            case 5:
                editor.putString("phone_num_5", txt);
                break;
            case 6:
                editor.putString("phone_num_6", txt);
                break;
            case 7:
                editor.putString("phone_num_7", txt);
                break;
            case 8:
                editor.putString("phone_num_8", txt);
                break;
            case 9:
                editor.putString("phone_num_9", txt);
                break;
        }
        editor.commit();
    }


    private void xuLyGoi(View view, int n) {
        SharedPreferences sP = this.getActivity().getSharedPreferences("phone_num_to_call", Context.MODE_PRIVATE);
        String phone="";
        switch (n)
        {
            case 1: phone = sP.getString("phone_num_1", ""); break;
            case 2: phone = sP.getString("phone_num_2", ""); break;
            case 3: phone = sP.getString("phone_num_3", ""); break;
            case 4: phone = sP.getString("phone_num_4", ""); break;
            case 5: phone = sP.getString("phone_num_5", ""); break;
            case 6: phone = sP.getString("phone_num_6", ""); break;
            case 7: phone = sP.getString("phone_num_7", ""); break;
            case 8: phone = sP.getString("phone_num_8", ""); break;
            case 9: phone = sP.getString("phone_num_9", ""); break;
        }

        Toast.makeText(getContext(), phone, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getActivity().startActivity(intent);
//
    }

    private void addControls(View view) {
        imgNum1= (ImageView) view.findViewById(R.id.imgNum1);
        imgNum2= (ImageView) view.findViewById(R.id.imgNum2);
        imgNum3= (ImageView) view.findViewById(R.id.imgNum3);
        imgNum4= (ImageView) view.findViewById(R.id.imgNum4);
        imgNum5= (ImageView) view.findViewById(R.id.imgNum5);
        imgNum6= (ImageView) view.findViewById(R.id.imgNum6);
        imgNum7= (ImageView) view.findViewById(R.id.imgNum7);
        imgNum8= (ImageView) view.findViewById(R.id.imgNum8);
        imgNum9= (ImageView) view.findViewById(R.id.imgNum9);

    }

}

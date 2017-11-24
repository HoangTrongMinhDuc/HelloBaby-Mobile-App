package com.example.admin.xmltest.models;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.xmltest.Comic.ComicProfile;
import com.example.admin.xmltest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 11/23/2017.
 */

public class CallButtonAdapter extends ArrayAdapter<ButtonNumberCall>{
    private Context mContext;
    private int resource;
    private List<ButtonNumberCall> listButton;


    EditText input;
    String txt="";

    public CallButtonAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ButtonNumberCall> listButton) {
        super(context, resource, listButton);
        this.mContext = context;
        this.resource = resource;
        this.listButton = listButton;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_button_call, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgButton = (ImageView) convertView.findViewById(R.id.imgButtonCall);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ButtonNumberCall buttonNumberCall = listButton.get(position);

        int img = 0;
        if(buttonNumberCall.getLinkPhoto()=="bear")
            img=R.mipmap.icon_bear;
        else
        if(buttonNumberCall.getLinkPhoto()=="dog")
            img=R.mipmap.icon_dog;
        else
        if(buttonNumberCall.getLinkPhoto()=="owl")
            img=R.mipmap.icon_owl;
        else
        if(buttonNumberCall.getLinkPhoto()=="elephant")
            img=R.mipmap.icon_elephant;
        else
        if(buttonNumberCall.getLinkPhoto()=="gigraffy")
            img=R.mipmap.icon_gigraffy;
        else
        if(buttonNumberCall.getLinkPhoto()=="koala")
            img=R.mipmap.icon_koala;
        else
        if(buttonNumberCall.getLinkPhoto()=="lion")
            img=R.mipmap.icon_lion;
        else
        if(buttonNumberCall.getLinkPhoto()=="monkey")
            img=R.mipmap.icon_monkey;
        else
        if(buttonNumberCall.getLinkPhoto()=="water_seal")
            img=R.mipmap.icon_water_seal;
       

        Picasso.with(this.mContext).load(img).resize(150,150).into(viewHolder.imgButton);
        viewHolder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGoi(position);
            }
        });
        viewHolder.imgButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                xuLyNhapSo(view,position);
                return false;
            }
        });

        return convertView;
    }
    private void xuLyNhapSo(View v, final int i)
    {

        AlertDialog.Builder buider=new AlertDialog.Builder(getContext());
        buider.setTitle("Nhập số điện thoại");
        buider.setIcon(R.mipmap.icon_running_rabbit);
        EditText editText=new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        input=editText;
        buider.setView(input);

        buider.setPositiveButton("Nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt=input.getText().toString();
                listButton.get(i).setPhone(txt);
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


        AlertDialog ad = buider.create();
        ad.show();

    }


    private void savePhone(String txt, int i) {
        SharedPreferences sP = getContext().getSharedPreferences("phone_num_to_call", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString("phone_num_"+i,txt);
        editor.commit();
    }

    private void xuLyGoi(int n) {
        SharedPreferences sP = getContext().getSharedPreferences("phone_num_to_call", Context.MODE_PRIVATE);
        String phone="";

          phone = sP.getString("phone_num_"+n, "");


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
        getContext().startActivity(intent);
//
    }

    public class ViewHolder {
        ImageView imgButton;
    }
}

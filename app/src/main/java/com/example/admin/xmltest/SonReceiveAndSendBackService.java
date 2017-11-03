package com.example.admin.xmltest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 9/16/2017.
 */

public class SonReceiveAndSendBackService extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Object[] pdus= (Object[]) bundle.get("pdus");
        for (int i=1;i<pdus.length;i++)
        {
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) pdus[i]);
            String noidung=smsMessage.getMessageBody();
            String phone=smsMessage.getOriginatingAddress();
            Toast.makeText(context,"Số phone="+phone+"\nNội dung:"+noidung,Toast.LENGTH_SHORT).show();
            if(noidung.indexOf("+hello123")!=-1){
                Toast.makeText(context,"Đang lấy vị trí...",Toast.LENGTH_LONG).show();
            }
        }

    }


}

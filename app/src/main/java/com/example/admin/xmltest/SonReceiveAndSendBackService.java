package com.example.admin.xmltest;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by admin on 9/16/2017.
 */

public class SonReceiveAndSendBackService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Service Started", Toast.LENGTH_LONG).show();
        Bundle extras=intent.getExtras();
        if(extras!=null){

        }
    }
}

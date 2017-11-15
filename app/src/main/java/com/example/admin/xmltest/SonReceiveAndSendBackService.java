package com.example.admin.xmltest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.app.Service;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 9/16/2017.
 */

public class SonReceiveAndSendBackService extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.DONUT)

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    private Location currentBestLocation = null;


    @Override
    public void onReceive(final Context context, Intent intent) {
//        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Toast.makeText(context, location.getLatitude() +":"+location.getLongitude(), Toast.LENGTH_LONG).show();
//                latitude = location.getLatitude();
//                longitude = location.getLongitude();
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        else {
//            locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
//        }
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();

        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            } else {
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String noidung = smsMessage.getMessageBody();
            String phone = smsMessage.getOriginatingAddress();
            Toast.makeText(context, "Số phone=" + phone + "\nNội dung:" + noidung, Toast.LENGTH_LONG).show();


            if (noidung.indexOf("+hello123") != -1) {
//                currentBestLocation = getLastBestLocation(context);
                mLocationManager = (LocationManager) context
                        .getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    if(isGPSEnabled)
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 5, mLocationListener);
                    else if(isNetworkEnabled)
                         mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,5,mLocationListener);
                    Location gotLoc = mLocationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(gotLoc!=null)
                        currentBestLocation=gotLoc;
                    Toast.makeText(context, "Mobile Location (NW):"+currentBestLocation,
                            Toast.LENGTH_LONG).show();







                //SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage(phone, null, "+hello123:" + currentBestLocation.getLongitude() + "$" + currentBestLocation.getAltitude(), null, null);
                //Toast.makeText(context,longitude +"$"+latitude,Toast.LENGTH_LONG).show();
            }

        }

    }

    /*private Location getLastBestLocation(Context context) {

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        }
        else {
            return locationNet;
        }
    }

*/

}

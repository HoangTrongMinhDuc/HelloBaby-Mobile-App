package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView naviationView;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    SharedPreferences pre,pre2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth=FirebaseAuth.getInstance();
        addControls();
        addEvents();
        addFloatingActionButton();
        addNavigationView();
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        if(pre2.getString("forObject","").equals("son"))
//        {
//            Menu menu=navigationView.getMenu();
//            menu.findItem(R.id.nav_slideshow).setEnabled(false);
//        }
//    }

    private void addNavigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void addFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void addEvents() {
        pre=getSharedPreferences("login_data",MODE_PRIVATE);
        pre2=getSharedPreferences("for",MODE_PRIVATE);



        //Thu khoa 1 nut trong menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView tvName = (TextView)hView.findViewById(R.id.tvName);
        TextView tvPass= (TextView) hView.findViewById(R.id.tvEmail_);

        mUser=FirebaseAuth.getInstance().getCurrentUser();
        tvName.setText(mUser.getEmail());
        tvPass.setText(mUser.getDisplayName()+"----");
//        tvName.setText(pre.getString("username",""));
//        tvPass.setText(pre.getString("username",""));

        if(pre2.getString("forObject","").equals("son"))
        {
            Menu menu=navigationView.getMenu();
            menu.findItem(R.id.nav_slideshow).setEnabled(false);
        }

        //Khoi tao va chay Service SonReceiAndSendBackService




        //Tao fragment 1 ban dau khi vao
        ChucNang1TestFrag fragment1=new ChucNang1TestFrag();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment1);
        fragmentTransaction.commit();

    }

    private void addControls() {


    }

//Ghi đè

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(NavigationDrawer.this,SetupAccountActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //Tao fragment 1
            ChucNang1Frag fragment1=new ChucNang1Frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment1);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {
            //Tao fragment 2
            ChucNang2Frag fragment2=new ChucNang2Frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment2);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            ChucNang3Frag fragment3=new ChucNang3Frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment3);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_manage) {
            ChucNang4Frag fragment4=new ChucNang4Frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment4);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.tangent.assessment.tangentboard.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.database.DatabaseHelper;
import com.tangent.assessment.tangentboard.model.UserData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainDrawerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getMyDetails();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

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
        getMenuInflater().inflate(R.menu.main_drawer, menu);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void getMyDetails(){

        Observable<UserData> observable = RetrofitClient.getInstance(this, true).getApiService().getMyDetails();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserData userData) {

                        Log.i(TAG, "Got user details for " + userData.getFirstName());

                        //CHECK IF USER DETAILS ALREADY EXIST, IF SO REMOVE THEM
                        checkIfUserDetailsExist();

                        //INSERT NEW USERDETAILS
                        insertMyDetails(userData);
                    }
                });
    }

    protected void checkIfUserDetailsExist(){

        Cursor cursor = getContentResolver().query(DatabaseHelper.LOGIN_CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            getContentResolver().delete(DatabaseHelper.TOKEN_CONTENT_URI, null, null);
            cursor.close();
        }
    }

    protected void insertMyDetails(UserData userData){

        Log.i(TAG, "Inserting my user details");

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ID, userData.getId());
        values.put(DatabaseHelper.EMAIL, userData.getEmail());
        values.put(DatabaseHelper.FIRST_NAME, userData.getFirstName());
        values.put(DatabaseHelper.LAST_NAME, userData.getLastName());
        values.put(DatabaseHelper.USERNAME, userData.getUsername());
        values.put(DatabaseHelper.IS_ACTIVE, userData.isActive());
        values.put(DatabaseHelper.IS_STAFF, userData.isStaff());
        values.put(DatabaseHelper.IS_SUPERUSER, userData.isIsSuperuser());

        getContentResolver().insert(DatabaseHelper.TOKEN_CONTENT_URI, values);

    }
}

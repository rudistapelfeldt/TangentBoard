package com.tangent.assessment.tangentboard.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.database.DatabaseHelper;
import com.tangent.assessment.tangentboard.fragment.AdminFragment;
import com.tangent.assessment.tangentboard.fragment.ProfileFragment;
import com.tangent.assessment.tangentboard.fragment.StatisticsFragment;
import com.tangent.assessment.tangentboard.model.UserData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    ProfileFragment.OnFragmentInteractionListener,
                    AdminFragment.OnFragmentInteractionListener,
                    StatisticsFragment.OnFragmentInteractionListener{

    private static final String TAG = MainDrawerActivity.class.getSimpleName();

    private Fragment mFragment;

    private TextView mName, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GET LOGGED IN USER'S DETAILS AND SAVE TO SQLITE DATABASE
        getMyDetails();

        //LOAD ADMINFRAGMENT AS HOME SCREEN
        mFragment = new AdminFragment();
        addFragment(mFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        mName = (TextView)header.findViewById(R.id.menu_tv_name);
        mEmail = (TextView)header.findViewById(R.id.menu_tv_email);
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

        if (id == R.id.nav_profile) {

            mFragment = new ProfileFragment();

        } else if (id == R.id.nav_statistics) {

            mFragment = new StatisticsFragment();

        } else if (id == R.id.nav_home){

            mFragment = new AdminFragment();

        }

        //ADD FRAGMENT TO MAINACTIVITY
        addFragment(mFragment);
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
            getContentResolver().delete(DatabaseHelper.LOGIN_CONTENT_URI, null, null);
            cursor.close();
        }
    }

    protected void insertMyDetails(UserData userData){

        Log.i(TAG, "Inserting my user details");

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USERNAME, userData.getUsername());
        values.put(DatabaseHelper.IS_SUPERUSER, userData.isIsSuperuser());
        values.put(DatabaseHelper.IS_STAFF, userData.isStaff());
        values.put(DatabaseHelper.ID, userData.getId());
        values.put(DatabaseHelper.LAST_NAME, userData.getLastName());
        values.put(DatabaseHelper.EMAIL, userData.getEmail());
        values.put(DatabaseHelper.IS_ACTIVE, userData.isActive());
        values.put(DatabaseHelper.FIRST_NAME, userData.getFirstName());
        getContentResolver().insert(DatabaseHelper.LOGIN_CONTENT_URI, values);

        setDrawerTextViews(userData);

    }

    protected void setDrawerTextViews(UserData userData){
        mName.setText(userData.getFirstName() + " " + userData.getLastName());
        mEmail.setText(userData.getEmail());
    }

    protected void addFragment(Fragment newFragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_main, newFragment).commitAllowingStateLoss();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

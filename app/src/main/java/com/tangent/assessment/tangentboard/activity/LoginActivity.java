package com.tangent.assessment.tangentboard.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.model.LoginData;
import com.tangent.assessment.tangentboard.utility.Constants;
import com.tangent.assessment.tangentboard.utility.Util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferences.Editor mEditor;

    private EditText mUsername;

    private EditText mPassword;

    private Button mLoginBtn;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SHARED PREFERENCES REFERENCES
        mSharedPreferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);

        mEditor = mSharedPreferences.edit();

        //PROGRESS DIALOG REFERENCE
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.progress_login));
        mProgressDialog.setCanceledOnTouchOutside(false);

        //EDITTEXT REFERENCES
        mUsername = (EditText)findViewById(R.id.usernameEditText);

        mPassword = (EditText)findViewById(R.id.passwordEditText);

        //BUTTON REFERENCE
        mLoginBtn = (Button)findViewById(R.id.loginButton);

        //BUTTON CLICK ACTION
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    protected void attemptLogin(){

        mProgressDialog.show();

        String username = mUsername.getText().toString().trim();

        String password = mPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty() ){
            mProgressDialog.dismiss();
            Util.showToast(this, getString(R.string.error_no_credentials));
        } else if (!username.equals(getString(R.string.correct_credentials))
                || !password.equals(getString(R.string.correct_credentials))){
            mProgressDialog.dismiss();
            Util.showToast(this, getString(R.string.error_incorrect_credentials));
        }else{

            Observable<LoginData> observable = RetrofitClient.getInstance(this, false).getApiService()
                    .getAuthToken(username, password);

            observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginData>() {
                        @Override
                        public void onCompleted() {

                            //OPEN MAINDRAWERACTIVITY
                            Intent homeIntent = new Intent(LoginActivity.this, MainDrawerActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "ERROR: " + e.getMessage());
                        }

                        @Override
                        public void onNext(LoginData loginData) {
                            Log.i(TAG, "Login successful. Token = " + loginData.getToken());

                            mEditor.putBoolean(Constants.IS_LOGGED_IN, true).apply();

                            mProgressDialog.dismiss();
                        }
                    });
        }
    }

}

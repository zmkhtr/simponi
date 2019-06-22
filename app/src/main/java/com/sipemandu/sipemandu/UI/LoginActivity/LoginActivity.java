package com.sipemandu.sipemandu.UI.LoginActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
import com.sipemandu.sipemandu.Utils.SessionManager;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    private EditText mEmail, mPassword;
    private Context context;
    private SessionManager sessionManager;
    private ProgressBar mProgress;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();
        sessionManager = new SessionManager(context);

        presenter = new LoginPresenter(this, new GetLoginInteractor());

        initView();
        hideActionbar();
        checkSession();
        AndroidNetworking.initialize(getApplicationContext());
    }

    public void initView() {
        mEmail = findViewById(R.id.edtLoginEmail);
        mPassword = findViewById(R.id.edtLoginPassword);
        mProgress = findViewById(R.id.pbLoginLoading);

        Button mProsesLogin = findViewById(R.id.btnLoginProceed);


        mProsesLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                presenter.onLogin(email, password);
            }
        });
    }

    public void checkSession() {
        if (sessionManager.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void hideActionbar() {
        ActionBar ab = getSupportActionBar();
        ab.hide();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("login_email", mEmail.getText().toString());
        outState.putString("login_password", mPassword.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mEmail.setText(savedInstanceState.getString("login_email"));
        mPassword.setText(savedInstanceState.getString("login_password"));

    }

    @Override
    public void showProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoginSuccess(String message) {
        Log.d(TAG, "onLoginSuccess: init");
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onLoginError(String message) {
        Log.d(TAG, "onLoginError: init");
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onLoginErrorTrue(String message) {
        Log.d(TAG, "onLoginErrorTrue: init");
        Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onLoginErrorFalse(String token) {
        Log.d(TAG, "onLoginErrorFalse: init");
        sessionManager.setLogin(true, token);
        sessionManager.setUserEmail(mEmail.getText().toString());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}

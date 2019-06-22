package com.sipemandu.sipemandu.UI.LoginActivity;

import org.json.JSONObject;

public class LoginPresenter implements LoginContract.Presenter,LoginContract.GetLoginInteractor.OnFinishedListener {

    private LoginContract.View loginView;
    private LoginContract.GetLoginInteractor loginInteractor;

    public LoginPresenter(LoginContract.View loginView, LoginContract.GetLoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }


    @Override
    public void onLogin(String email, String password) {
        User user = new User(email, password);
        int loginCode = user.isValidData();
        if (loginView != null){
            loginView.showProgressBar();
            if(loginCode == 0) {
                loginView.onLoginError("Mohon masukkan email anda");
                loginView.hideProgressBar();
            } else if(loginCode == 1) {
                loginView.onLoginError("Mohon masukkan format email yang benar");
                loginView.hideProgressBar();
            } else if(loginCode == 2) {
                loginView.onLoginError("Password minimal 6 karakter");
                loginView.hideProgressBar();
            } else {
                loginInteractor.getLoginProceed(this, email, password);
            }
        }

    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.onLoginSuccess("Login success");
        }
    }

    @Override
    public void onError(Throwable e) {
        if (loginView != null) {
            loginView.hideProgressBar();
        }
    }

    @Override
    public void onErrorTrue(String message) {
        if (loginView != null) {
            loginView.onLoginErrorTrue(message);
            loginView.hideProgressBar();
        }
    }

    @Override
    public void onErrorFalse(String message) {
        if (loginView != null) {
            loginView.onLoginErrorFalse(message);
            loginView.hideProgressBar();
        }
    }

}

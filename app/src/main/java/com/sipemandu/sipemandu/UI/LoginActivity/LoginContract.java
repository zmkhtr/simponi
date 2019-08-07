package com.sipemandu.sipemandu.UI.LoginActivity;

import org.json.JSONObject;

public interface LoginContract {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void onLoginSuccess(String message);
        void onLoginError(String message);
        void onLoginErrorTrue(String message);
        void onLoginErrorFalse(String token, String name);
    }

    interface GetLoginInteractor{
        interface OnFinishedListener{
            void onSuccess(JSONObject jsonObject);
            void onError(Throwable e);
            void onErrorTrue(String message);
            void onErrorFalse(String token, String name);
        }
        void getLoginProceed(OnFinishedListener onFinishedListener, String email, String password);
    }

    interface Presenter{
        void onLogin(String email, String password);
    }
}

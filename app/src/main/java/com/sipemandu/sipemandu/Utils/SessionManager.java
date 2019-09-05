package com.sipemandu.sipemandu.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Shared preferences file name1
    private static final String PREF_NAME = "SipemanduLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String USER_TOKEN = "userToken";
    private static final String ID_NFC = "idNFC";
    private static final String USER_EMAIL = "userEmail";
    private static final String ERROR_NFC = "errorNFC";


    private static final String NIK_ORTU = "nikOrtu";
    private static final String NAMA_ORTU = "namaOrtu";
    private static final String TANGGAL_LAHIR_ORTU = "tanggalLahirOrtu";


    private static final String ID_ANAK = "idAnak";
    private static final String NAMA_ANAK = "namaAnak";
    private static final String USIA_ANAK = "usiaAnak";

    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String userToken) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(USER_TOKEN, userToken);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }
    public void setNFC(String idNFC) {
        editor.putString(ID_NFC, idNFC);
        editor.commit();
    }

    public void setUsiaAnak(String usia) {
        editor.putString(USIA_ANAK, usia);
        editor.commit();
    }


    public void setUserEmail(String email) {
        editor.putString(USER_EMAIL, email);
        editor.commit();
    }
    public void setErrorNfc(boolean errorNfc) {
        editor.putBoolean(ERROR_NFC, errorNfc);
        editor.commit();
    }

    public void setDataOrtu(String nik, String nama, String tanggaLahir){
        editor.putString(NIK_ORTU, nik);
        editor.putString(NAMA_ORTU, nama);
        editor.putString(TANGGAL_LAHIR_ORTU, tanggaLahir);
        editor.commit();
    }

    public void setDataAnak(int idAnak, String namaAnak, String namaOrtu){
        editor.putInt(ID_ANAK, idAnak);
        editor.putString(NAMA_ANAK, namaAnak);
        editor.putString(NAMA_ORTU, namaOrtu);
        editor.commit();
    }


    public String getUsiaAnak() {
        return pref.getString(USIA_ANAK, null);
    }

    public String getNikOrtu(){
        return pref.getString(NIK_ORTU, null);
    }
    public String getNamaOrtu(){
        return pref.getString(NAMA_ORTU, null);
    }
    public String getTanggalLahirOrtu(){
        return pref.getString(TANGGAL_LAHIR_ORTU, null);
    }


    public String getNamaAnak(){
        return pref.getString(NAMA_ANAK, null);
    }
    public int getIdAnak(){
        return pref.getInt(ID_ANAK, -1);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public String getUserToken(){
        return pref.getString(USER_TOKEN, null);
    }
    public String getNFC(){
        return pref.getString(ID_NFC, null);
    }
    public String getUserEmail(){
        return pref.getString(USER_EMAIL, null);
    }
    public boolean isErrorNfc(){
        return pref.getBoolean(ERROR_NFC, false);
    }
}



package com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImunisasiResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("imunisasi")
    @Expose
    private Imunisasi imunisasi;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Imunisasi getImunisasi() {
        return imunisasi;
    }

    public void setImunisasi(Imunisasi imunisasi) {
        this.imunisasi = imunisasi;
    }

}

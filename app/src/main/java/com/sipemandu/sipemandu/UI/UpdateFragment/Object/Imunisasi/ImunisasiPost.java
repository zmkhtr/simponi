package com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImunisasiPost {
    @SerializedName("hepatitisb")
    @Expose
    private String hepatitisb;

    @SerializedName("bcg")
    @Expose
    private String bcg;

    @SerializedName("polio")
    @Expose
    private String polio;

    @SerializedName("campak")
    @Expose
    private String campak;

    @SerializedName("dpt")
    @Expose
    private String dpt;

    public ImunisasiPost(String hepatitisb, String bcg, String polio, String campak, String dpt) {
        this.hepatitisb = hepatitisb;
        this.bcg = bcg;
        this.polio = polio;
        this.campak = campak;
        this.dpt = dpt;
    }

    public String getHepatitisb() {
        return hepatitisb;
    }

    public void setHepatitisb(String hepatitisb) {
        this.hepatitisb = hepatitisb;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getPolio() {
        return polio;
    }

    public void setPolio(String polio) {
        this.polio = polio;
    }

    public String getCampak() {
        return campak;
    }

    public void setCampak(String campak) {
        this.campak = campak;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }
}


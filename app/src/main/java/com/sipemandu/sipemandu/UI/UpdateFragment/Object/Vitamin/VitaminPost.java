package com.sipemandu.sipemandu.UI.UpdateFragment.Object.Vitamin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VitaminPost {
    @SerializedName("vita_biru")
    @Expose
    private String vita_biru;

    @SerializedName("vita_merah")
    @Expose
    private String vita_merah;

    public VitaminPost(String vita_biru, String vita_merah) {
        this.vita_biru = vita_biru;
        this.vita_merah = vita_merah;
    }

    public String getVita_biru() {
        return vita_biru;
    }

    public void setVita_biru(String vita_biru) {
        this.vita_biru = vita_biru;
    }

    public String getVita_merah() {
        return vita_merah;
    }

    public void setVita_merah(String vita_merah) {
        this.vita_merah = vita_merah;
    }
}

package com.sipemandu.sipemandu.UI.UpdateFragment.Object.Vitamin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vit {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("anak_id")
    @Expose
    private Integer anakId;
    @SerializedName("vita_biru")
    @Expose
    private String vitaBiru;
    @SerializedName("vita_merah1")
    @Expose
    private String vitaMerah1;
    @SerializedName("vita_merah2")
    @Expose
    private String vitaMerah2;
    @SerializedName("vita_merah3")
    @Expose
    private Object vitaMerah3;
    @SerializedName("vita_merah4")
    @Expose
    private Object vitaMerah4;
    @SerializedName("vita_merah5")
    @Expose
    private Object vitaMerah5;
    @SerializedName("vita_merah6")
    @Expose
    private Object vitaMerah6;
    @SerializedName("vita_merah7")
    @Expose
    private Object vitaMerah7;
    @SerializedName("vita_merah8")
    @Expose
    private Object vitaMerah8;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnakId() {
        return anakId;
    }

    public void setAnakId(Integer anakId) {
        this.anakId = anakId;
    }

    public String getVitaBiru() {
        return vitaBiru;
    }

    public void setVitaBiru(String vitaBiru) {
        this.vitaBiru = vitaBiru;
    }

    public String getVitaMerah1() {
        return vitaMerah1;
    }

    public void setVitaMerah1(String vitaMerah1) {
        this.vitaMerah1 = vitaMerah1;
    }

    public String getVitaMerah2() {
        return vitaMerah2;
    }

    public void setVitaMerah2(String vitaMerah2) {
        this.vitaMerah2 = vitaMerah2;
    }

    public Object getVitaMerah3() {
        return vitaMerah3;
    }

    public void setVitaMerah3(Object vitaMerah3) {
        this.vitaMerah3 = vitaMerah3;
    }

    public Object getVitaMerah4() {
        return vitaMerah4;
    }

    public void setVitaMerah4(Object vitaMerah4) {
        this.vitaMerah4 = vitaMerah4;
    }

    public Object getVitaMerah5() {
        return vitaMerah5;
    }

    public void setVitaMerah5(Object vitaMerah5) {
        this.vitaMerah5 = vitaMerah5;
    }

    public Object getVitaMerah6() {
        return vitaMerah6;
    }

    public void setVitaMerah6(Object vitaMerah6) {
        this.vitaMerah6 = vitaMerah6;
    }

    public Object getVitaMerah7() {
        return vitaMerah7;
    }

    public void setVitaMerah7(Object vitaMerah7) {
        this.vitaMerah7 = vitaMerah7;
    }

    public Object getVitaMerah8() {
        return vitaMerah8;
    }

    public void setVitaMerah8(Object vitaMerah8) {
        this.vitaMerah8 = vitaMerah8;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

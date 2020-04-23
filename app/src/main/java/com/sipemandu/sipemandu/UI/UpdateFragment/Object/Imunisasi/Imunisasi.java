package com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imunisasi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("anak_id")
    @Expose
    private Integer anakId;
    @SerializedName("hepatitisb_1")
    @Expose
    private String hepatitisb1;
    @SerializedName("hepatitisb_2")
    @Expose
    private Object hepatitisb2;
    @SerializedName("hepatitisb_3")
    @Expose
    private Object hepatitisb3;
    @SerializedName("bcg")
    @Expose
    private String bcg;
    @SerializedName("polio_1")
    @Expose
    private Object polio1;
    @SerializedName("polio_2")
    @Expose
    private Object polio2;
    @SerializedName("polio_3")
    @Expose
    private Object polio3;
    @SerializedName("polio_4")
    @Expose
    private Object polio4;
    @SerializedName("dpt_1")
    @Expose
    private Object dpt1;
    @SerializedName("dpt_2")
    @Expose
    private Object dpt2;
    @SerializedName("dpt_3")
    @Expose
    private Object dpt3;
    @SerializedName("campak")
    @Expose
    private Object campak;
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

    public String getHepatitisb1() {
        return hepatitisb1;
    }

    public void setHepatitisb1(String hepatitisb1) {
        this.hepatitisb1 = hepatitisb1;
    }

    public Object getHepatitisb2() {
        return hepatitisb2;
    }

    public void setHepatitisb2(Object hepatitisb2) {
        this.hepatitisb2 = hepatitisb2;
    }

    public Object getHepatitisb3() {
        return hepatitisb3;
    }

    public void setHepatitisb3(Object hepatitisb3) {
        this.hepatitisb3 = hepatitisb3;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public Object getPolio1() {
        return polio1;
    }

    public void setPolio1(Object polio1) {
        this.polio1 = polio1;
    }

    public Object getPolio2() {
        return polio2;
    }

    public void setPolio2(Object polio2) {
        this.polio2 = polio2;
    }

    public Object getPolio3() {
        return polio3;
    }

    public void setPolio3(Object polio3) {
        this.polio3 = polio3;
    }

    public Object getPolio4() {
        return polio4;
    }

    public void setPolio4(Object polio4) {
        this.polio4 = polio4;
    }

    public Object getDpt1() {
        return dpt1;
    }

    public void setDpt1(Object dpt1) {
        this.dpt1 = dpt1;
    }

    public Object getDpt2() {
        return dpt2;
    }

    public void setDpt2(Object dpt2) {
        this.dpt2 = dpt2;
    }

    public Object getDpt3() {
        return dpt3;
    }

    public void setDpt3(Object dpt3) {
        this.dpt3 = dpt3;
    }

    public Object getCampak() {
        return campak;
    }

    public void setCampak(Object campak) {
        this.campak = campak;
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

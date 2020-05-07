package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imunisasi implements Parcelable {

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
    private String hepatitisb2;
    @SerializedName("hepatitisb_3")
    @Expose
    private String hepatitisb3;
    @SerializedName("bcg")
    @Expose
    private String bcg;
    @SerializedName("polio_1")
    @Expose
    private String polio1;
    @SerializedName("polio_2")
    @Expose
    private String polio2;
    @SerializedName("polio_3")
    @Expose
    private String polio3;
    @SerializedName("polio_4")
    @Expose
    private String polio4;
    @SerializedName("dpt_1")
    @Expose
    private String dpt1;
    @SerializedName("dpt_2")
    @Expose
    private String dpt2;
    @SerializedName("dpt_3")
    @Expose
    private String dpt3;
    @SerializedName("campak")
    @Expose
    private String campak;
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

    public String getHepatitisb2() {
        return hepatitisb2;
    }

    public void setHepatitisb2(String hepatitisb2) {
        this.hepatitisb2 = hepatitisb2;
    }

    public String getHepatitisb3() {
        return hepatitisb3;
    }

    public void setHepatitisb3(String hepatitisb3) {
        this.hepatitisb3 = hepatitisb3;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getPolio1() {
        return polio1;
    }

    public void setPolio1(String polio1) {
        this.polio1 = polio1;
    }

    public String getPolio2() {
        return polio2;
    }

    public void setPolio2(String polio2) {
        this.polio2 = polio2;
    }

    public String getPolio3() {
        return polio3;
    }

    public void setPolio3(String polio3) {
        this.polio3 = polio3;
    }

    public String getPolio4() {
        return polio4;
    }

    public void setPolio4(String polio4) {
        this.polio4 = polio4;
    }

    public String getDpt1() {
        return dpt1;
    }

    public void setDpt1(String dpt1) {
        this.dpt1 = dpt1;
    }

    public String getDpt2() {
        return dpt2;
    }

    public void setDpt2(String dpt2) {
        this.dpt2 = dpt2;
    }

    public String getDpt3() {
        return dpt3;
    }

    public void setDpt3(String dpt3) {
        this.dpt3 = dpt3;
    }

    public String getCampak() {
        return campak;
    }

    public void setCampak(String campak) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.anakId);
        dest.writeString(this.hepatitisb1);
        dest.writeString(this.hepatitisb2);
        dest.writeString(this.hepatitisb3);
        dest.writeString(this.bcg);
        dest.writeString(this.polio1);
        dest.writeString(this.polio2);
        dest.writeString(this.polio3);
        dest.writeString(this.polio4);
        dest.writeString(this.dpt1);
        dest.writeString(this.dpt2);
        dest.writeString(this.dpt3);
        dest.writeString(this.campak);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Imunisasi() {
    }

    protected Imunisasi(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.anakId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hepatitisb1 = in.readString();
        this.hepatitisb2 = in.readString();
        this.hepatitisb3 = in.readString();
        this.bcg = in.readString();
        this.polio1 = in.readString();
        this.polio2 = in.readParcelable(String.class.getClassLoader());
        this.polio3 = in.readParcelable(String.class.getClassLoader());
        this.polio4 = in.readParcelable(String.class.getClassLoader());
        this.dpt1 = in.readString();
        this.dpt2 = in.readString();
        this.dpt3 = in.readParcelable(String.class.getClassLoader());
        this.campak = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Imunisasi> CREATOR = new Parcelable.Creator<Imunisasi>() {
        @Override
        public Imunisasi createFromParcel(Parcel source) {
            return new Imunisasi(source);
        }

        @Override
        public Imunisasi[] newArray(int size) {
            return new Imunisasi[size];
        }
    };
}
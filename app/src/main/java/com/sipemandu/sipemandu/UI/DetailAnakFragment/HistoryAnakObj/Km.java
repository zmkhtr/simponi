package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Km implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("anak_id")
    @Expose
    private Integer anakId;
    @SerializedName("bb")
    @Expose
    private Double bb;
    @SerializedName("ket_bb")
    @Expose
    private String ketBb;
    @SerializedName("tb")
    @Expose
    private Double tb;
    @SerializedName("ket_tb")
    @Expose
    private String ketTb;
    @SerializedName("usia")
    @Expose
    private String usia;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("bulan")
    @Expose
    private Integer bulan;


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

    public Double getBb() {
        return bb;
    }

    public void setBb(Double bb) {
        this.bb = bb;
    }

    public String getKetBb() {
        return ketBb;
    }

    public void setKetBb(String ketBb) {
        this.ketBb = ketBb;
    }

    public Double getTb() {
        return tb;
    }

    public void setTb(Double tb) {
        this.tb = tb;
    }

    public String getKetTb() {
        return ketTb;
    }

    public void setKetTb(String ketTb) {
        this.ketTb = ketTb;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
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

    public Integer getBulan() {
        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    public Km(Integer id, Integer anakId, Double bb, String ketBb, Double tb, String ketTb, String usia, String createdAt, String updatedAt, Integer bulan) {
        this.id = id;
        this.anakId = anakId;
        this.bb = bb;
        this.ketBb = ketBb;
        this.tb = tb;
        this.ketTb = ketTb;
        this.usia = usia;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bulan = bulan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.anakId);
        dest.writeValue(this.bb);
        dest.writeString(this.ketBb);
        dest.writeValue(this.tb);
        dest.writeString(this.ketTb);
        dest.writeString(this.usia);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeValue(this.bulan);
    }

    public Km() {
    }

    protected Km(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.anakId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bb = (Double) in.readValue(Double.class.getClassLoader());
        this.ketBb = in.readString();
        this.tb = (Double) in.readValue(Integer.class.getClassLoader());
        this.ketTb = in.readString();
        this.usia = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.bulan = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Km> CREATOR = new Parcelable.Creator<Km>() {
        @Override
        public Km createFromParcel(Parcel source) {
            return new Km(source);
        }

        @Override
        public Km[] newArray(int size) {
            return new Km[size];
        }
    };
}
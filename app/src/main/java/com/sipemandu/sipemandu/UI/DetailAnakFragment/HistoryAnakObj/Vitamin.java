package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vitamin implements Parcelable {

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
    private String vitaMerah3;
    @SerializedName("vita_merah4")
    @Expose
    private String vitaMerah4;
    @SerializedName("vita_merah5")
    @Expose
    private String vitaMerah5;
    @SerializedName("vita_merah6")
    @Expose
    private String vitaMerah6;
    @SerializedName("vita_merah7")
    @Expose
    private String vitaMerah7;
    @SerializedName("vita_merah8")
    @Expose
    private String vitaMerah8;
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

    public String getVitaMerah3() {
        return vitaMerah3;
    }

    public void setVitaMerah3(String vitaMerah3) {
        this.vitaMerah3 = vitaMerah3;
    }

    public String getVitaMerah4() {
        return vitaMerah4;
    }

    public void setVitaMerah4(String vitaMerah4) {
        this.vitaMerah4 = vitaMerah4;
    }

    public String getVitaMerah5() {
        return vitaMerah5;
    }

    public void setVitaMerah5(String vitaMerah5) {
        this.vitaMerah5 = vitaMerah5;
    }

    public String getVitaMerah6() {
        return vitaMerah6;
    }

    public void setVitaMerah6(String vitaMerah6) {
        this.vitaMerah6 = vitaMerah6;
    }

    public String getVitaMerah7() {
        return vitaMerah7;
    }

    public void setVitaMerah7(String vitaMerah7) {
        this.vitaMerah7 = vitaMerah7;
    }

    public String getVitaMerah8() {
        return vitaMerah8;
    }

    public void setVitaMerah8(String vitaMerah8) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.anakId);
        dest.writeString(this.vitaBiru);
        dest.writeString(this.vitaMerah1);
        dest.writeString(this.vitaMerah2);
        dest.writeString(this.vitaMerah3);
        dest.writeString(this.vitaMerah4);
        dest.writeString(this.vitaMerah5);
        dest.writeString(this.vitaMerah6);
        dest.writeString(this.vitaMerah7);
        dest.writeString(this.vitaMerah8);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Vitamin() {
    }

    protected Vitamin(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.anakId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.vitaBiru = in.readString();
        this.vitaMerah1 = in.readString();
        this.vitaMerah2 = in.readString();
        this.vitaMerah3 = in.readString();
        this.vitaMerah4 = in.readString();
        this.vitaMerah5 = in.readString();
        this.vitaMerah6 = in.readString();
        this.vitaMerah7 = in.readString();
        this.vitaMerah8 = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Vitamin> CREATOR = new Parcelable.Creator<Vitamin>() {
        @Override
        public Vitamin createFromParcel(Parcel source) {
            return new Vitamin(source);
        }

        @Override
        public Vitamin[] newArray(int size) {
            return new Vitamin[size];
        }
    };
}
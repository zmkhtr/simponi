package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Makanan implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("anak_id")
    @Expose
    private Integer anakId;
    @SerializedName("nama_makanan")
    @Expose
    private String namaMakanan;
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

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
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
        dest.writeString(this.namaMakanan);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Makanan() {
    }

    protected Makanan(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.anakId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.namaMakanan = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Makanan> CREATOR = new Parcelable.Creator<Makanan>() {
        @Override
        public Makanan createFromParcel(Parcel source) {
            return new Makanan(source);
        }

        @Override
        public Makanan[] newArray(int size) {
            return new Makanan[size];
        }
    };
}



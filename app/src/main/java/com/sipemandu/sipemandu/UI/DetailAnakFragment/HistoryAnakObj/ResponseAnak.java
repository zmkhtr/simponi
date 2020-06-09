package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAnak implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("anak")
    @Expose
    private Anak anak;
    @SerializedName("kms")
    @Expose
    private List<Km> kms = null;
    @SerializedName("imunisasi")
    @Expose
    private Imunisasi imunisasi;
    @SerializedName("vitamin")
    @Expose
    private Vitamin vitamin;
    @SerializedName("makanan")
    @Expose
    private List<Makanan> makanan = null;



    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Anak getAnak() {
        return anak;
    }

    public void setAnak(Anak anak) {
        this.anak = anak;
    }

    public List<Km> getKms() {
        return kms;
    }

    public void setKms(List<Km> kms) {
        this.kms = kms;
    }

    public Imunisasi getImunisasi() {
        return imunisasi;
    }

    public void setImunisasi(Imunisasi imunisasi) {
        this.imunisasi = imunisasi;
    }

    public Vitamin getVitamin() {
        return vitamin;
    }

    public void setVitamin(Vitamin vitamin) {
        this.vitamin = vitamin;
    }

    public List<Makanan> getMakanan() {
        return makanan;
    }

    public void setMakanan(List<Makanan> makanan) {
        this.makanan = makanan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.error);
        dest.writeParcelable(this.anak, flags);
        dest.writeList(this.kms);
        dest.writeParcelable(this.imunisasi, flags);
        dest.writeParcelable(this.vitamin, flags);
        dest.writeList(this.makanan);
    }

    public ResponseAnak() {
    }

    protected ResponseAnak(Parcel in) {
        this.error = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.anak = in.readParcelable(Anak.class.getClassLoader());
        this.kms = new ArrayList<Km>();
        in.readList(this.kms, Km.class.getClassLoader());
        this.imunisasi = in.readParcelable(Imunisasi.class.getClassLoader());
        this.vitamin = in.readParcelable(Vitamin.class.getClassLoader());
        this.makanan = in.readParcelable(Makanan.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseAnak> CREATOR = new Parcelable.Creator<ResponseAnak>() {
        @Override
        public ResponseAnak createFromParcel(Parcel source) {
            return new ResponseAnak(source);
        }

        @Override
        public ResponseAnak[] newArray(int size) {
            return new ResponseAnak[size];
        }
    };
}
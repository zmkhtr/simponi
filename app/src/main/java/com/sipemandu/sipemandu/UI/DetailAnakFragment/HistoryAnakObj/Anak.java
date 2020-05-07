package com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Anak implements Parcelable {

    @SerializedName("nama_ortu")
    @Expose
    private String namaOrtu;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ortu_id")
    @Expose
    private Double ortuId;
    @SerializedName("posyandu_id")
    @Expose
    private Double posyanduId;
    @SerializedName("nik_anak")
    @Expose
    private String nikAnak;
    @SerializedName("nama_anak")
    @Expose
    private String namaAnak;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("bb_lahir")
    @Expose
    private Integer bbLahir;
    @SerializedName("tb_lahir")
    @Expose
    private Integer tbLahir;
    @SerializedName("asi_external")
    @Expose
    private Integer asiExternal;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOrtuId() {
        return ortuId;
    }

    public void setOrtuId(Double ortuId) {
        this.ortuId = ortuId;
    }

    public Double getPosyanduId() {
        return posyanduId;
    }

    public void setPosyanduId(Double posyanduId) {
        this.posyanduId = posyanduId;
    }

    public String getNikAnak() {
        return nikAnak;
    }

    public void setNikAnak(String nikAnak) {
        this.nikAnak = nikAnak;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Integer getBbLahir() {
        return bbLahir;
    }

    public void setBbLahir(Integer bbLahir) {
        this.bbLahir = bbLahir;
    }

    public Integer getTbLahir() {
        return tbLahir;
    }

    public void setTbLahir(Integer tbLahir) {
        this.tbLahir = tbLahir;
    }

    public Integer getAsiExternal() {
        return asiExternal;
    }

    public void setAsiExternal(Integer asiExternal) {
        this.asiExternal = asiExternal;
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
        dest.writeString(this.namaOrtu);
        dest.writeValue(this.id);
        dest.writeValue(this.ortuId);
        dest.writeValue(this.posyanduId);
        dest.writeString(this.nikAnak);
        dest.writeString(this.namaAnak);
        dest.writeString(this.jenisKelamin);
        dest.writeString(this.tglLahir);
        dest.writeValue(this.bbLahir);
        dest.writeValue(this.tbLahir);
        dest.writeValue(this.asiExternal);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Anak() {
    }

    protected Anak(Parcel in) {
        this.namaOrtu = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ortuId = (Double) in.readValue(Integer.class.getClassLoader());
        this.posyanduId = (Double) in.readValue(Integer.class.getClassLoader());
        this.nikAnak = in.readString();
        this.namaAnak = in.readString();
        this.jenisKelamin = in.readString();
        this.tglLahir = in.readString();
        this.bbLahir = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tbLahir = (Integer) in.readValue(Integer.class.getClassLoader());
        this.asiExternal = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Anak> CREATOR = new Parcelable.Creator<Anak>() {
        @Override
        public Anak createFromParcel(Parcel source) {
            return new Anak(source);
        }

        @Override
        public Anak[] newArray(int size) {
            return new Anak[size];
        }
    };
}
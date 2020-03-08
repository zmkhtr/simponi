package com.sipemandu.sipemandu.Model;

import android.os.Parcel;
import android.os.Parcelable;

//@Entity(tableName = "data_kms_detail_table")
public class DataKMSDetail implements Parcelable {

//    @PrimaryKey
    private int id;
    private Double bb;
    private String ket_bb;
    private Double tb;
    private String ket_tb;

    public DataKMSDetail(int id, Double bb, String ket_bb, Double tb, String ket_tb) {
        this.id = id;
        this.bb = bb;
        this.ket_bb = ket_bb;
        this.tb = tb;
        this.ket_tb = ket_tb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBb() {
        return bb;
    }

    public void setBb(Double bb) {
        this.bb = bb;
    }

    public String getKet_bb() {
        return ket_bb;
    }

    public void setKet_bb(String ket_bb) {
        this.ket_bb = ket_bb;
    }

    public Double getTb() {
        return tb;
    }

    public void setTb(Double tb) {
        this.tb = tb;
    }

    public String getKet_tb() {
        return ket_tb;
    }

    public void setKet_tb(String ket_tb) {
        this.ket_tb = ket_tb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeValue(this.bb);
        dest.writeString(this.ket_bb);
        dest.writeValue(this.tb);
        dest.writeString(this.ket_tb);
    }

    protected DataKMSDetail(Parcel in) {
        this.id = in.readInt();
        this.bb = (Double) in.readValue(Double.class.getClassLoader());
        this.ket_bb = in.readString();
        this.tb = (Double) in.readValue(Double.class.getClassLoader());
        this.ket_tb = in.readString();
    }

    public static final Parcelable.Creator<DataKMSDetail> CREATOR = new Parcelable.Creator<DataKMSDetail>() {
        @Override
        public DataKMSDetail createFromParcel(Parcel source) {
            return new DataKMSDetail(source);
        }

        @Override
        public DataKMSDetail[] newArray(int size) {
            return new DataKMSDetail[size];
        }
    };
}

package com.sipemandu.sipemandu.Room.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(tableName = "data_kms_detail_table")
public class DataKMSDetail {

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
}

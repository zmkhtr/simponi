package com.sipemandu.sipemandu.Room.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(tableName = "data_kms_table")
public class DataKMS {

//    @PrimaryKey
    private int id;
    private String namaOrtu;
    private String namaAnak;
    private int bb;
    private String ket_bb;
    private int tb;
    private String ket_tb;

    public DataKMS(String namaOrtu, String namaAnak, int id, int bb, String ket_bb, int tb, String ket_tb) {
        this.namaOrtu = namaOrtu;
        this.namaAnak = namaAnak;
        this.id = id;
        this.bb = bb;
        this.ket_bb = ket_bb;
        this.tb = tb;
        this.ket_tb = ket_tb;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public int getId() {
        return id;
    }

    public int getBb() {
        return bb;
    }

    public String getKet_bb() {
        return ket_bb;
    }

    public int getTb() {
        return tb;
    }

    public String getKet_tb() {
        return ket_tb;
    }
}

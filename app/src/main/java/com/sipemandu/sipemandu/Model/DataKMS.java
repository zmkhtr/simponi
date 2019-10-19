package com.sipemandu.sipemandu.Model;

//@Entity(tableName = "data_kms_table")
public class DataKMS {

//    @PrimaryKey
    private int id;
    private String namaOrtu;
    private String namaAnak;
    private Double bb;
    private String ket_bb;
    private Double tb;
    private String ket_tb;
    private String nik_anak;
    private String tgl_lahir;

    public DataKMS(String namaOrtu, String namaAnak, int id, Double bb, String ket_bb, Double tb, String ket_tb, String nik_anak, String tgl_lahir) {
        this.namaOrtu = namaOrtu;
        this.namaAnak = namaAnak;
        this.id = id;
        this.bb = bb;
        this.ket_bb = ket_bb;
        this.tb = tb;
        this.ket_tb = ket_tb;
        this.nik_anak = nik_anak;
        this.tgl_lahir = tgl_lahir;
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

    public Double getBb() {
        return bb;
    }

    public String getKet_bb() {
        return ket_bb;
    }

    public Double getTb() {
        return tb;
    }

    public String getKet_tb() {
        return ket_tb;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public void setBb(Double bb) {
        this.bb = bb;
    }

    public void setKet_bb(String ket_bb) {
        this.ket_bb = ket_bb;
    }

    public void setTb(Double tb) {
        this.tb = tb;
    }

    public void setKet_tb(String ket_tb) {
        this.ket_tb = ket_tb;
    }

    public String getNik_anak() {
        return nik_anak;
    }

    public void setNik_anak(String nik_anak) {
        this.nik_anak = nik_anak;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}

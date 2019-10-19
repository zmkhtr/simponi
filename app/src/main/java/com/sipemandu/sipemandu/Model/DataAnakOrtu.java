package com.sipemandu.sipemandu.Model;

//@Entity(tableName = "data_anak_ortu_table")
public class DataAnakOrtu {

//    @PrimaryKey
    private int id;
    private String namaOrtu;
    private int ortuId;
    private String nikAnak;
    private String namaAnak;
    private String jenisKelamin;
    private String tanggalLahir;
    private Double bbLahir;
    private Double tbLahir;
    private String asiEkslusif;

    public DataAnakOrtu(String namaOrtu, int id, int ortuId, String nikAnak, String namaAnak, String jenisKelamin, String tanggalLahir, Double bbLahir, Double tbLahir, String asiEkslusif) {
        this.namaOrtu = namaOrtu;
        this.id = id;
        this.ortuId = ortuId;
        this.nikAnak = nikAnak;
        this.namaAnak = namaAnak;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.bbLahir = bbLahir;
        this.tbLahir = tbLahir;
        this.asiEkslusif = asiEkslusif;
    }



    public String getNamaOrtu() {
        return namaOrtu;
    }

    public int getId() {
        return id;
    }

    public int getOrtuId() {
        return ortuId;
    }

    public String getNikAnak() {
        return nikAnak;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public Double getBbLahir() {
        return bbLahir;
    }

    public Double getTbLahir() {
        return tbLahir;
    }

    public String isAsiEkslusif() {
        return asiEkslusif;
    }

    public String getAsiEkslusif() {
        return asiEkslusif;
    }

    public void setAsiEkslusif(String asiEkslusif) {
        this.asiEkslusif = asiEkslusif;
    }
}

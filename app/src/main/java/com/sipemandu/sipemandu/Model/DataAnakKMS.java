package com.sipemandu.sipemandu.Model;

//@Entity(tableName = "data_anak_kms_table")
public class DataAnakKMS {
    //no_nfc, nik, nama_ortu, tgl_lahir_ortu, nik_anak, nama_anak, jenis_kelamin, tgl_lahir_anak, bb_lahir, tb_lahir, asi_external

//    @PrimaryKey
    private int idAnak;
    private String nikAnak;
    private String namaAnak;
    private String jenisKelamin;
    private String tanggalLahir;
    private Double beratBadan;
    private Double tinggiBadan;
    private String asiEksklusif;

    public DataAnakKMS(int idAnak, String nikAnak, String namaAnak, String jenisKelamin, String tanggalLahir, Double beratBadan, Double tinggiBadan, String asiEksklusif) {
        this.idAnak = idAnak;
        this.nikAnak = nikAnak;
        this.namaAnak = namaAnak;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.asiEksklusif = asiEksklusif;
    }

    public void setIdAnak(int idAnak) {
        this.idAnak = idAnak;
    }

    public int getIdAnak() {
        return idAnak;
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

    public Double getBeratBadan() {
        return beratBadan;
    }

    public Double getTinggiBadan() {
        return tinggiBadan;
    }

    public String getAsiEksklusif() {
        return asiEksklusif;
    }
}

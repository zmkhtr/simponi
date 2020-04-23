package com.sipemandu.sipemandu.Utils;

public class URLs {
    public static final String BASE_URL = "http://simponi.id/api";
    public static final String END_POINT_LOGIN = "/login";
    //field : email, password
    public static final String END_POINT_DAFTAR = "/daftar";
    //field : no_nfc, nik, nama_ortu, tgl_lahir_ortu, nik_anak, nik_anak, jenis_kelamin, tgl_lahir_anak, bb_lahir, bb_lahir, asi_external
    public static final String END_POINT_SEARCH_KTP = "/searchKTP/"; // + {no_nfc}
    public static final String END_POINT_SEARCH_NAMA_ANAK = "/searchNama/"; // + {nama_anak}
    public static final String END_POINT_ENTRY_POSYANDU = "/data/kms/"; // + {id}  -->id == id anak
    public static final String END_POINT_LAPORAN_HARIAN = "/data/kms/harian/"; // + {created_at} format tgl == 2019-05-10
    public static final String END_POINT_LAPORAN_ANAK = "/data/kms/anak/"; // + {id} -->id == id anak

    public static final String END_POINT_INPUT_VITAMIN = "/data/vitamin/"; // + {id} -->id == id anak
    //vita_biru:2020-04-23
    //vita_merah:2020-04-23
    public static final String END_POINT_INPUT_IMUNISASI = "/data/imunisasi/"; // + {id} -->id == id anak
    //hepatitisb:2020-04-23
    //bcg:2020-04-23
    //polio:2020-04-23
    //campak:2020-04-23
    //dpt:2020-04-23
    public static final String END_POINT_INPUT_MAKANAN = "/data/makanan/"; // + {id} -->id == id anak
    //makanan:telor gulung
    public static final String END_POINT_HISTORY_ANAK = "/data/kms/anak/"; // + {id} -->id == id anak

//    http://simponi.id/api/data/kms/anak
}

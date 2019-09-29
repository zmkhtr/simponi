package com.sipemandu.sipemandu.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.sipemandu.sipemandu.Room.Model.DataAnak;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ReportUtil {

    public static Period calculateAge(String date) {
        LocalDate birthdate = new LocalDate(Date.valueOf(date));      //Birth date
        Log.d(TAG, "calculateAge: gga " + birthdate);
        LocalDate now = new LocalDate();                        //Today's date
        Log.d(TAG, "calculateAge: " + birthdate);
        return new Period(birthdate, now, PeriodType.yearMonthDay());
    }

    public static Period calculateAgePenimbangan(String date, String tglPenimbangan) {
        LocalDate birthdate = new LocalDate(Date.valueOf(date));      //Birth date
        LocalDate penimbangan = new LocalDate(Date.valueOf(tglPenimbangan));                        //Today's date
        Log.d(TAG, "calculateAge: " + birthdate);
        return new Period(birthdate, penimbangan, PeriodType.yearMonthDay());
    }


    public static boolean exportToExcel(List<DataKMS> dataAnaks, String date, String namaPosyandu) {

        String Fnamexls = "Laporan" + new LocalDate() + "_" + System.currentTimeMillis() + ".xls";
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/laporan");
        directory.mkdirs();
        File file = new File(directory, Fnamexls);

        Log.d(TAG, "exportToExcel: " + directory);

        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;
        try {
            int a = 1;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //workbook.createSheet("Report", 0);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            List<Label> labelListNama = new ArrayList<>();
            List<Label> labelListNIK = new ArrayList<>();
            List<Label> labelListDate = new ArrayList<>();
            List<Label> labelListBB = new ArrayList<>();
            List<Label> labelListTB = new ArrayList<>();
            List<Label> labelListUsia = new ArrayList<>();
            List<Period> periods = new ArrayList<>();
            List<String> bb = new ArrayList<>();
            List<String> tb = new ArrayList<>();
            List<String> usia = new ArrayList<>();

            try {
                for (int i = 0; i < dataAnaks.size(); i++) {
                    DataKMS dataKMS = dataAnaks.get(i);

                    Label labelJudul = new Label(0, 0, "Laporan Tanggal");
                    Label labelTanggal = new Label(0, 1, date);
                    Label labelNamaPosyandu = new Label(0,2,namaPosyandu);
                    Label labelAnak = new Label(1, 0, "Nama Anak");
                    Label labelNIK = new Label(2, 0, "NIK Anak");
                    Label labelTanggalInput = new Label(3, 0, "Tanggal Input");
                    Label labelBB = new Label(4, 0, "Berat Badan");
                    Label labelTB = new Label(5, 0, "Tinggi Badan");
                    Label labelUsia = new Label(6, 0, "Usia Saat Penimbangan");


                    Period period = calculateAgePenimbangan(dataKMS.getTgl_lahir(), date);

                    periods.add(calculateAgePenimbangan(dataKMS.getTgl_lahir(), date));
                    String usiaHitung = period.getYears() + " tahun " + period.getMonths() + " Bulan";

                    labelListNama.add(new Label(1,i+1,dataKMS.getNamaAnak()));
                    labelListNIK.add(new Label(2,i+1,dataKMS.getNik_anak()));
                    labelListDate.add(new Label(3,i+1,date));
                    labelListBB.add(new Label(4,i+1,String.valueOf(dataKMS.getBb())));
                    labelListTB.add(new Label(5,i+1,String.valueOf(dataKMS.getTb())));
                    labelListUsia.add(new Label(6,i+1,usiaHitung));


                    sheet.addCell(labelTanggal);
                    sheet.addCell(labelNamaPosyandu);
                    sheet.addCell(labelJudul);
                    sheet.addCell(labelAnak);
                    sheet.addCell(labelNIK);
                    sheet.addCell(labelTanggalInput);
                    sheet.addCell(labelBB);
                    sheet.addCell(labelTB);
                    sheet.addCell(labelUsia);

                    sheet.addCell(labelListNama.get(i));
                    sheet.addCell(labelListNIK.get(i));
                    sheet.addCell(labelListDate.get(i));
                    sheet.addCell(labelListBB.get(i));
                    sheet.addCell(labelListTB.get(i));
                    sheet.addCell(labelListUsia.get(i));

//                    Log.d(TAG, "exportToExcel: coba " + labelList.get(i));
                }
//                sheet.addCell(label);
//                sheet.addCell(label1);
//                sheet.addCell(label0);
//                sheet.addCell(label4);
//                sheet.addCell(label3);
//                sheet.addCell(label5);
//                sheet.addCell(label6);
//                sheet.addCell(label7);
//                sheet.addCell(label8);
//                sheet.addCell(label9);
            } catch (RowsExceededException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (WriteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //createExcel(excelSheet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }


}

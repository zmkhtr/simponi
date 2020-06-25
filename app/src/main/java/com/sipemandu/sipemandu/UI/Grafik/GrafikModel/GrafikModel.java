package com.sipemandu.sipemandu.UI.Grafik.GrafikModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrafikModel {

    @SerializedName("Month")
    @Expose
    private Integer month;
    @SerializedName("2nd (2.3rd)")
    @Expose
    private float _2nd23rd;
    @SerializedName("5th")
    @Expose
    private float _5th;
    @SerializedName("10th")
    @Expose
    private float _10th;
    @SerializedName("25th")
    @Expose
    private float _25th;
    @SerializedName("50th")
    @Expose
    private float _50th;
    @SerializedName("75th")
    @Expose
    private float _75th;
    @SerializedName("90th")
    @Expose
    private float _90th;
    @SerializedName("95th")
    @Expose
    private float _95th;
    @SerializedName("98th (97.7th)")
    @Expose
    private float _98th977th;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public float get2nd23rd() {
        return _2nd23rd;
    }

    public void set2nd23rd(float _2nd23rd) {
        this._2nd23rd = _2nd23rd;
    }

    public float get5th() {
        return _5th;
    }

    public void set5th(float _5th) {
        this._5th = _5th;
    }

    public float get10th() {
        return _10th;
    }

    public void set10th(float _10th) {
        this._10th = _10th;
    }

    public float get25th() {
        return _25th;
    }

    public void set25th(float _25th) {
        this._25th = _25th;
    }

    public float get50th() {
        return _50th;
    }

    public void set50th(float _50th) {
        this._50th = _50th;
    }

    public float get75th() {
        return _75th;
    }

    public void set75th(float _75th) {
        this._75th = _75th;
    }

    public float get90th() {
        return _90th;
    }

    public void set90th(float _90th) {
        this._90th = _90th;
    }

    public float get95th() {
        return _95th;
    }

    public void set95th(float _95th) {
        this._95th = _95th;
    }

    public float get98th977th() {
        return _98th977th;
    }

    public void set98th977th(float _98th977th) {
        this._98th977th = _98th977th;
    }

}
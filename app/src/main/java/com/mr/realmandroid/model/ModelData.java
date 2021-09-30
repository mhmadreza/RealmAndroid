package com.mr.realmandroid.model;

import io.realm.RealmObject;

public class ModelData extends RealmObject {

    int id;
    String nama;
    int umur;
    String alamat;

    public ModelData(int id, String nama, int umur, String alamat) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
    }

    public ModelData(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

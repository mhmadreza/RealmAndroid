package com.mr.realmandroid.helper;

import android.content.Context;
import android.widget.Toast;

import com.mr.realmandroid.model.ModelData;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmHelper {

    private Context context;
    private Realm realm;
    private RealmResults<ModelData> realmResults;

    public RealmHelper(Context context){
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    /* Data awal/default */
    public void inputDataAwal(){

        ModelData modelData = new ModelData();
        modelData.setId(1);
        modelData.setNama("Muhamad Reza");
        modelData.setUmur(24);
        modelData.setAlamat("Depok");

        realm.beginTransaction();
        realm.copyToRealm(modelData);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil di Input", Toast.LENGTH_SHORT).show();

    }

    /* Tampil Data */
    public ArrayList<ModelData> tampilData(){

        ArrayList<ModelData> dataTampil = new ArrayList<>();
        realmResults = realm.where(ModelData.class).findAll();
        realmResults.sort("id", Sort.ASCENDING);

        if (realmResults.size() > 0){
            for (int i=0; i < realmResults.size(); i++){
                ModelData loopData = new ModelData();
                loopData.setId(realmResults.get(i).getId());
                loopData.setNama(realmResults.get(i).getNama());
                loopData.setUmur(realmResults.get(i).getUmur());
                loopData.setAlamat(realmResults.get(i).getAlamat());
                dataTampil.add(loopData);
            }
        }
        return dataTampil;
    }

    public void tambahData(String nama, int umur, String alamat){
        ModelData tambahData = new ModelData();
        tambahData.setId((int) (System.currentTimeMillis() / 1000));
        tambahData.setNama(nama);
        tambahData.setUmur(umur);
        tambahData.setAlamat(alamat);

        realm.beginTransaction();
        realm.copyToRealm(tambahData);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

    }

    public void updateData(int id, String nama, int umur, String alamat){

        realm.beginTransaction();
        ModelData updateData = realm.where(ModelData.class).equalTo("id", id).findFirst();
        updateData.setNama(nama);
        updateData.setUmur(umur);
        updateData.setAlamat(alamat);
        realm.copyToRealm(updateData);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil di Update!", Toast.LENGTH_SHORT).show();

    }

    public void deleteData(int id){

        realm.beginTransaction();
        RealmResults<ModelData> deleteData = realm.where(ModelData.class).equalTo("id", id).findAll();
        deleteData.deleteAllFromRealm();
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil di DiHapus!", Toast.LENGTH_SHORT).show();

    }



}

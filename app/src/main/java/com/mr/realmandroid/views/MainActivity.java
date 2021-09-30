package com.mr.realmandroid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr.realmandroid.R;
import com.mr.realmandroid.adapter.DataApadater;
import com.mr.realmandroid.helper.RealmHelper;
import com.mr.realmandroid.model.ModelData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ModelData> data = new ArrayList<>();
    private RealmHelper helper;
    private TextView tvToday, TxtNoData;
    private FloatingActionButton fab;
    private String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        fab = findViewById(R.id.fab);
        TxtNoData = findViewById(R.id.txtNoData);
        tvToday = findViewById(R.id.tvDate);
        recyclerView = findViewById(R.id.rv_data);
        helper = new RealmHelper(MainActivity.this);
        recyclerView.setAdapter(new DataApadater(MainActivity.this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")){
            hariIni = "Minggu";
        }else if (hariIni.equalsIgnoreCase("monday")){
            hariIni = "Senin";
        }else if (hariIni.equalsIgnoreCase("tuesday")){
            hariIni = "Selasa";
        }else if (hariIni.equalsIgnoreCase("wednesday")){
            hariIni = "Rabu";
        }else if (hariIni.equalsIgnoreCase("thursday")){
            hariIni = "Kamis";
        }else if (hariIni.equalsIgnoreCase("friday")){
            hariIni = "Jum'at";
        }else if (hariIni.equalsIgnoreCase("saturday")){
            hariIni = "Sabtu";
        }

        setData();
        getToday();

    }

    private void setData() {

        data = helper.tampilData();
        if (data.size() == 0){
            TxtNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            TxtNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new DataApadater(MainActivity.this, data));
        }

    }

    private void getToday() {

        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1){
            bulan = "Januari";
        }else if (month == 2){
            bulan = "Februari";
        }else if (month == 3){
            bulan = "Maret";
        }else if (month == 4){
            bulan = "April";
        }else if (month == 5){
            bulan = "Mei";
        }else if (month == 6){
            bulan = "Juni";
        }else if (month == 7){
            bulan = "Juli";
        }else if (month == 8){
            bulan = "Agustus";
        }else if (month == 9){
            bulan = "September";
        }else if (month == 10){
            bulan = "Oktober";
        }else if (month == 11){
            bulan = "November";
        }else if (month == 12){
            bulan = "Desember";
        }

        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + year;
        tvToday.setText(formatFix);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }
}
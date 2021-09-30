package com.mr.realmandroid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mr.realmandroid.R;
import com.mr.realmandroid.helper.RealmHelper;

public class TambahActivity extends AppCompatActivity {

    private EditText etTambahNama, etTambahUmur, etTambahAlamat;
    private Button btnTambah;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        initToolbar();
        initTambahData();

    }

    private void initToolbar() {

        Toolbar tbMW = findViewById(R.id.tbAdd);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");

    }

    private void initTambahData() {

        etTambahNama = findViewById(R.id.et_namaTmbh);
        etTambahUmur = findViewById(R.id.et_umurTmbh);
        etTambahAlamat = findViewById(R.id.et_alamatTmbh);
        btnTambah = findViewById(R.id.btn_tambah);

        helper = new RealmHelper(this);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTambahNama.getText().toString().isEmpty() || etTambahUmur.getText().toString().isEmpty()
                        || etTambahAlamat.getText().toString().isEmpty()){
                    Toast.makeText(TambahActivity.this, "Mohon lengkapi Data !", Toast.LENGTH_SHORT).show();
                }else {
                    String nama = etTambahNama.getText().toString();
                    int umur = Integer.parseInt(etTambahUmur.getText().toString());
                    String alamat = etTambahAlamat.getText().toString();
                    helper.tambahData(nama, umur, alamat);
                    Toast.makeText(TambahActivity.this, "Data Terkirim", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
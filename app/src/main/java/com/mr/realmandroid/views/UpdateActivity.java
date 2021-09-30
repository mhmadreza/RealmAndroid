package com.mr.realmandroid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mr.realmandroid.R;
import com.mr.realmandroid.helper.RealmHelper;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {

    private EditText etUpdateNama, etUpdateUmur, etUpdateAlamat;
    private Button btnUpdate, btnHapus;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initToolbarUpdate();
        initUdateData();
    }

    private void initToolbarUpdate() {

        Toolbar tbMW = findViewById(R.id.tbUpdate);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Data");

    }

    private void initUdateData(){

        final int id = getIntent().getIntExtra("DATA_ID", 0);
        String nama = getIntent().getStringExtra("DATA_NAMA");
        int umur = getIntent().getIntExtra("DATA_UMUR", 0);
        String alamat = getIntent().getStringExtra("DATA_ALAMAT");

        etUpdateNama = findViewById(R.id.et_namaUpdate);
        etUpdateUmur = findViewById(R.id.et_umurUpdate);
        etUpdateAlamat = findViewById(R.id.et_alamatUpdate);
        btnUpdate = findViewById(R.id.btn_update);
        btnHapus = findViewById(R.id.btn_hapus);

        etUpdateNama.setText(nama);
        etUpdateUmur.setText(String.valueOf(umur));
        etUpdateAlamat.setText(alamat);

        helper = new RealmHelper(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etUpdateNama.getText().toString();
                int umur = Integer.parseInt(etUpdateUmur.getText().toString());
                String alamat = etUpdateAlamat.getText().toString();
                helper.updateData(id, nama, umur, alamat);
                Toast.makeText(UpdateActivity.this, "Update Berhasil!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteData(id);
                Toast.makeText(UpdateActivity.this, "Data berhasil di delete!", Toast.LENGTH_SHORT).show();
                finish();
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
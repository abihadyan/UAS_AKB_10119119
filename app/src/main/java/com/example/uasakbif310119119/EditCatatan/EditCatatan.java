package com.example.uasakbif310119119.EditCatatan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasakbif310119119.Database.SQLite;
import com.example.uasakbif310119119.MainActivity;
import com.example.uasakbif310119119.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//NIM     : 10119119
//NAMA    : MUHAMMAD HADYAN NUR ADABI
//KELAS   : IF-3

public class EditCatatan extends AppCompatActivity {
    private EditText et_judul, et_kategori, et_isi;
    private Date date;
    private SimpleDateFormat dateFormat, monthFormat, yearFormat;
    private Button submitButton;
    private SQLite helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);

        helper = new SQLite(this);

        Bundle bundle = getIntent().getExtras();
        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        submitButton = findViewById(R.id.submitButton);
        et_judul = findViewById(R.id.judul);
        et_kategori = findViewById(R.id.kategori);
        et_isi = findViewById(R.id.isi);

        et_judul.setText(bundle.getString("Judul"));
        et_kategori.setText(bundle.getString("Kategori"));
        et_isi.setText(bundle.getString("Isi"));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = bundle.getString("Id");
                String judul = et_judul.getText().toString();
                String kategori = et_kategori.getText().toString();
                String isi = et_isi.getText().toString();
                String formattedDate = dateFormat.format(date);
                String formattedMonth = monthFormat.format(date);
                String formattedYear = yearFormat.format(date);
                if (TextUtils.isEmpty(judul)) {
                    et_judul.setError("Judul tidak boleh kosong");
                    et_judul.requestFocus();
                } else if (TextUtils.isEmpty(kategori)) {
                    et_kategori.setError("Kategori tidak boleh kosong");
                    et_kategori.requestFocus();
                } else if (TextUtils.isEmpty(isi)) {
                    et_isi.setError("Isi Catatan tidak boleh kosong");
                    et_isi.requestFocus();
                } else {
                    boolean isSuccess = helper.updateData(id, judul, kategori, isi, formattedDate, formattedMonth, formattedYear);

                    if (isSuccess) {
                        Toast.makeText(EditCatatan.this, "Catatan berhasil diubah", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditCatatan.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(EditCatatan.this, "Catatan gagal diubah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}

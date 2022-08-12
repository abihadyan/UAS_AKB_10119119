package com.example.uasakbif310119119.TambahCatatan;

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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//NIM     : 10119119
//NAMA    : MUHAMMAD HADYAN NUR ADABI
//KELAS   : IF-3

public class TambahCatatan extends AppCompatActivity {
    private EditText et_judul,et_kategori,et_isi;
    private Date date;
    private DateFormat dateFormat,monthFormat,yearFormat;
    private Button submitButton;
    private SQLite helper;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);

        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        submitButton = findViewById(R.id.submitButton);
        et_judul = findViewById(R.id.judul);
        et_kategori = findViewById(R.id.kategori);
        et_isi = findViewById(R.id.isi);

        helper = new SQLite(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = et_judul.getText().toString();
                String kategori = et_kategori.getText().toString();
                String isi = et_isi.getText().toString();
                String formattedDate = dateFormat.format(date);
                String formattedMonth = monthFormat.format(date);
                String formattedYear = yearFormat.format(date);

                if (TextUtils.isEmpty(judul)) {
                    et_judul.setError("Data tidak boleh kosong");
                    et_judul.requestFocus();
                } else if (TextUtils.isEmpty(kategori)) {
                    et_kategori.setError("Data tidak boleh kosong");
                    et_kategori.requestFocus();
                } else if (TextUtils.isEmpty(isi)) {
                    et_isi.setError("Data tidak boleh kosong");
                    et_isi.requestFocus();
                } else {
                    boolean isSuccess = helper.insertData(judul, kategori, isi, formattedDate, formattedMonth, formattedYear);

                    if (isSuccess) {
                        Toast.makeText(TambahCatatan.this, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(TambahCatatan.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(TambahCatatan.this, "Catatan gagal ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}

package com.example.uasakbif310119119.BotNav;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uasakbif310119119.Database.SQLite;
import com.example.uasakbif310119119.Interface.ListViewAdapter;
import com.example.uasakbif310119119.Model.Catatan;
import com.example.uasakbif310119119.R;
import com.example.uasakbif310119119.TambahCatatan.TambahCatatan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class CatatanHarianFragment extends Fragment  {
    private FloatingActionButton addButton;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<Catatan> listTask = new ArrayList<>();
    private SQLite helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_catatan_harian, container, false);

        addButton = root.findViewById(R.id.addButton);
        listView = root.findViewById(R.id.listView);

        helper = new SQLite(this.getActivity());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TambahCatatan.class));
            }
        });

        showData();

        return root;
    }
    public void showData() {
        listTask.clear();
        Cursor res = helper.getAllData();
        while (res.moveToNext()) {
            String id = res.getString(0);
            String judul = res.getString(1);
            String kategori = res.getString(2);
            String isi = res.getString(3);
            String date = res.getString(4);
            String month = res.getString(5);
            String year = res.getString(6);

            listTask.add(new Catatan(id, judul, kategori, isi, date, month, year));
        }

        listViewAdapter = new ListViewAdapter(listTask, getActivity());
        listView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
    }




}
package com.example.uasakbif310119119.Interface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.uasakbif310119119.Database.SQLite;
import com.example.uasakbif310119119.EditCatatan.EditCatatan;
import com.example.uasakbif310119119.Model.Catatan;
import com.example.uasakbif310119119.R;

import java.util.List;

//NIM     : 10119119
//NAMA    : MUHAMMAD HADYAN NUR ADABI
//KELAS   : IF-3

public class ListViewAdapter extends BaseAdapter {
    private List<Catatan> listCatatan;
    private Context context;
    private TextView judul, date, month, year, kategori, isi;
    private ImageView imageView;
    private Button editButton, deleteButton;
    private SQLite helper;


    public ListViewAdapter(List<Catatan> listCatatan, Context context) {
        this.listCatatan = listCatatan;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCatatan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_catatan,null);

        judul = view.findViewById(R.id.cardJudul);
        date = view.findViewById(R.id.cardDate);
        month = view.findViewById(R.id.cardMonth);
        year = view.findViewById(R.id.cardYear);
        kategori = view.findViewById(R.id.cardKategori);
        isi = view.findViewById(R.id.cardIsi);
        imageView = view.findViewById(R.id.imageview);
        helper = new SQLite(view.getContext());

        judul.setText(listCatatan.get(position).getJudul());
        date.setText(listCatatan.get(position).getDate());
        month.setText(listCatatan.get(position).getMonth());
        kategori.setText(listCatatan.get(position).getKategori());
        isi.setText(listCatatan.get(position).getIsi());
        String id = listCatatan.get(position).getId();

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(view.getContext(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                Toast.makeText(view.getContext(),"Catatan berhasil dihapus",
                                        Toast.LENGTH_SHORT).show();
                                helper.deleteData(id);
                                listCatatan.remove(position);
                                notifyDataSetChanged();
                                return true;
                            case R.id.edit:
                                Intent intent = new Intent(context, EditCatatan.class);
                                intent.putExtra("Id", listCatatan.get(position).getId());
                                intent.putExtra("Judul", listCatatan.get(position).getJudul());
                                intent.putExtra("Kategori", listCatatan.get(position).getKategori());
                                intent.putExtra("Isi", listCatatan.get(position).getIsi());
                                intent.putExtra("Date", listCatatan.get(position).getDate());
                                intent.putExtra("Month", listCatatan.get(position).getMonth());
                                intent.putExtra("Year", listCatatan.get(position).getYear());

                                context.startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.menu);
                popup.show();
            }

        });
        return view;
    }
}
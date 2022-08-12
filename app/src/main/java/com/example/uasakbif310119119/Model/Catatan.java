package com.example.uasakbif310119119.Model;

//NIM     : 10119119
//NAMA    : MUHAMMAD HADYAN NUR ADABI
//KELAS   : IF-3

    public class Catatan {
        private String id;
        private String judul;
        private String kategori;
        private String date;
        private String month;
        private String year;
        private String isi;

    public Catatan(String id, String judul, String kategori, String isi, String date, String month, String year) {
        this.id = id;
        this.judul = judul;
        this.kategori = kategori;
        this.isi = isi;
        this.date = date;
        this.month = month;
        this.year = year;
    }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKategori() {
            return kategori;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public String getDate() {
            return date;
        }

        public String getMonth() {
            return month;
        }
        public String getYear() {
            return year;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIsi() {
            return isi;
        }

        public void setIsi(String isi) {
            this.isi = isi;
        }
}

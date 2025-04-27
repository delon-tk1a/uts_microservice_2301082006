package com.teknologiinformasi.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "movie")
public class Movie {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   private String nama;
   private Double harga;
   private String deskripsi;
   private int stok;


   // Constructor tanpa parameter
   public Movie() {}


   // Constructor dengan parameter
   public Movie(String nama, Double harga, String deskripsi,int stok) {
       this.nama = nama;
       this.harga = harga;
       this.deskripsi = deskripsi;
       this.stok = stok;
   }


   // Getters dan Setters
   public Long getId() {
       return id;
   }


   public void setId(Long id) {
       this.id = id;
   }


   public String getNama() {
       return nama;
   }


   public void setNama(String nama) {
       this.nama = nama;
   }


   public Double getHarga() {
       return harga;
   }


   public void setHarga(Double harga) {
       this.harga = harga;
   }


   public String getDeskripsi() {
       return deskripsi;
   }

   public void setDeskripsi(String deskripsi) {
       this.deskripsi = deskripsi;
   }

   public int getStok() {
    return stok;
}


public void setStok(int stok) {
    this.stok = stok;
}
}

package com.example.doan_oderfood;


import java.io.Serializable;

public class MonAn implements Serializable {
    private int image;
    private String tenMonAn;
    private String gia;

    private String tongTien;
    private String soluong;


    public MonAn(int image, String tenMonAn, String gia) {
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
    }

    public MonAn(String tenMonAn, String soluong, String tongTien) {
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.soluong = soluong;
        this.tongTien = tongTien;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}

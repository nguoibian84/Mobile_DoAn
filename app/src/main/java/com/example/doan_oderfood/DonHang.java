package com.example.doan_oderfood;

public class DonHang {
    public String tenMonAn;
    public  int soLuong;
    public  int tongTien;

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int tongTienDonHang(){
        int s = 0;
        s += tongTien;
        return s;
    }
}

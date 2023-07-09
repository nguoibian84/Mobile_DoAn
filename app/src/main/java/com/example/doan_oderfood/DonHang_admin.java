package com.example.doan_oderfood;

import java.util.List;

public class DonHang_admin {
    private List<DonHang> lstDonHang;

    public DonHang_admin(List<DonHang> lstDonHang) {
        this.lstDonHang = lstDonHang;
    }

    public List<DonHang> getLstDonHang() {
        return lstDonHang;
    }

    public void setLstDonHang(List<DonHang> lstDonHang) {
        this.lstDonHang = lstDonHang;
    }
}

package com.hoaphph29102.test6.model;

public class KhachHang {
    String MaKH;
    String hoTen;
    int  tuoi;
    String sdt;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, int tuoi, String sdt) {
        MaKH = maKH;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}

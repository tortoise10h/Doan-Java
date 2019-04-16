package dto;

import java.util.*;

public class ChiTietHoaDonXuatDTO {
	private String maMon;
	private int maHoaDonXuat;
	private int donGia;
	private int soLuong;
	private int thanhTien;
	private boolean trangThai;
	
	public ChiTietHoaDonXuatDTO() {
		
	}
	
	public ChiTietHoaDonXuatDTO(String maMon, int maHoaDonXuat, int donGia, int soLuong, int thanhTien, boolean trangThai) {
		this.maMon = maMon;
		this.maHoaDonXuat = maHoaDonXuat;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
		
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public int getMaHoaDonXuat() {
		return maHoaDonXuat;
	}

	public void setMaHoaDonXuat(int maHoaDonXuat) {
		this.maHoaDonXuat = maHoaDonXuat;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

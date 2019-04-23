package dto;

import java.util.*;
public class ChiTietHoaDonNhapDTO {
	private int maHoaDonNhap;
	private String maNguyenLieu;
	private int donGia;
	private double soLuong;
	private int thanhTien;
	private String donViTinh;
	private boolean trangThai;
	
	public ChiTietHoaDonNhapDTO() {
		
	}
	
	public ChiTietHoaDonNhapDTO(int maHoaDonNhap, String maNguyenLieu, int donGia, double soLuong, String donViTinh, int thanhTien, boolean trangThai) {
		this.maHoaDonNhap = maHoaDonNhap;
		this.maNguyenLieu = maNguyenLieu;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
		this.donViTinh = donViTinh;
	}
	
	public int getMaHoaDonNhap() {
		return maHoaDonNhap;
	}
	public void setMaHoaDonNhap(int maHoaDonNhap) {
		this.maHoaDonNhap = maHoaDonNhap;
	}
	public String getMaNguyenLieu() {
		return maNguyenLieu;
	}
	public void setMaNguyenLieu(String maNguyenLieu) {
		this.maNguyenLieu = maNguyenLieu;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(double soLuong) {
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

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	
	
	
}

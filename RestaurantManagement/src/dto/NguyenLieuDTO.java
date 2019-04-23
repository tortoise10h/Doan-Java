package dto;

import java.util.*;

public class NguyenLieuDTO {
	private String maNguyenLieu;
	private String ten;
	private boolean loai;
	private String maNhaCungCap;
	private int gia;
	private double soLuong;
	private String donViTinh;
	private boolean trangThai;
	
	public NguyenLieuDTO() {}
	public NguyenLieuDTO(String maNguyenLieu, String ten, boolean loai, String maNhaCungCap, int gia, double soLuong, String donViTinh, boolean trangThai) {
		this.maNguyenLieu = maNguyenLieu;
		this.ten = ten;
		this.loai = loai;
		this.maNhaCungCap = maNhaCungCap;
		this.gia = gia;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.trangThai = trangThai;
	}
	public String getMaNguyenLieu() {
		return maNguyenLieu;
	}
	public void setMaNguyenLieu(String maNguyenLieu) {
		this.maNguyenLieu = maNguyenLieu;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public boolean isLoai() {
		return loai;
	}
	public void setLoai(boolean loai) {
		this.loai = loai;
	}
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

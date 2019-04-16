package dto;

import java.util.*;
public class MonDTO {
	private String maMon;
	private String maLoaiMon;
	private String tenMon;
	private int gia;
	private String donViTinh;
	private String imgLink;
	private boolean trangThai;
	
	public MonDTO() {
		
	}
	
	public MonDTO(String maMon, String maLoaiMon, String tenMon, int gia, String donViTinh, String imgLink, boolean trangThai) {
		this.maMon =  maMon;
		this.maLoaiMon = maLoaiMon;
		this.tenMon = tenMon;
		this.gia = gia;
		this.donViTinh = donViTinh;
		this.imgLink = imgLink;
		this.trangThai = trangThai;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaLoaiMon() {
		return maLoaiMon;
	}

	public void setMaLoaiMon(String maLoaiMon) {
		this.maLoaiMon = maLoaiMon;
	}
	
	
	
}

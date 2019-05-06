package dto;

import java.util.*;

public class ChiTietMonDTO {
	private String maMon;
	private String maNguyenLieu;
	private double soNguyenLieu;
	private boolean trangThai;
	
	public ChiTietMonDTO() {
		
	}
	
	public ChiTietMonDTO(String maMon, String maNguyenLieu, double soNguyenLieu, boolean trangThai) {
		this.maMon = maMon;
		this.maNguyenLieu = maNguyenLieu;
		this.soNguyenLieu = soNguyenLieu;
		this.trangThai = trangThai;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getMaNguyenLieu() {
		return maNguyenLieu;
	}

	public void setMaNguyenLieu(String maNguyenLieu) {
		this.maNguyenLieu = maNguyenLieu;
	}

	public double getSoNguyenLieu() {
		return soNguyenLieu;
	}

	public void setSoNguyenLieu(double soNguyenLieu) {
		this.soNguyenLieu = soNguyenLieu;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonXuatDTO {
	private int maHoaDon;
	private Date gioNhap;
	private Date gioXuat;
	private String maBan;
	private String maNv;
	private int tongTien;
	private boolean trangThai;
	
	public HoaDonXuatDTO() {}
	public HoaDonXuatDTO(int maHoaDon, Date gioNhap, Date gioXuat, String maBan, String maNv, int tongTien, boolean trangThai) {
		this.maHoaDon = maHoaDon;
		this.gioNhap = gioNhap;
		this.gioXuat = gioXuat;
		this.maBan = maBan;
		this.maNv = maNv;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		
	}
	
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getGioNhap() {
		return gioNhap;
	}
	public void setGioNhap(Date gioNhap) {
		this.gioNhap = gioNhap;
	}
	public Date getGioXuat() {
		return gioXuat;
	}
	public void setGioXuat(Date gioXuat) {
		this.gioXuat = gioXuat;
	}
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getMaNv() {
		return maNv;
	}
	public void setMaNv(String maNv) {
		this.maNv = maNv;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
	
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public static Date StringToDate(String dateStr) {
		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = dateFormat.parse(dateStr);
		}catch(ParseException e){
	        e.printStackTrace();
	    }
		
		return result;
	}
	
}

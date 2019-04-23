package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonNhapDTO {
	private int maHoaDonNhap;
	private Date gioXuat;
	private String maNhaCungCap;
	private String nhanVien;
	private int tongTien;
	private boolean trangThai;
	
	public HoaDonNhapDTO() {}
	public HoaDonNhapDTO(int maHoaDonNhap, Date gioXuat, String maNhaCungCap, String nhanVien, int tongTien, boolean trangThai) {
		this.maHoaDonNhap = maHoaDonNhap;
		this.gioXuat = gioXuat;
		this.maNhaCungCap = maNhaCungCap;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}
	
	
	
	public int getMaHoaDonNhap() {
		return maHoaDonNhap;
	}
	public void setMaHoaDonNhap(int maHoaDonNhap) {
		this.maHoaDonNhap = maHoaDonNhap;
	}
	public Date getGioXuat() {
		return gioXuat;
	}
	public void setGioXuat(Date gioXuat) {
		this.gioXuat = gioXuat;
	}
	
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public String getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(String nhanVien) {
		this.nhanVien = nhanVien;
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
	
	
	
}

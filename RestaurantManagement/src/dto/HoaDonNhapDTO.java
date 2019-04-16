package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonNhapDTO {
	private String maHoaDon;
	private Date thoiGianXuat;
	private String maNcc;
	private String maNv;
	private double tongTien;
	private boolean trangThai;
	
	public HoaDonNhapDTO() {}
	public HoaDonNhapDTO(String maHoaDon, Date thoiGianXuat, String maNcc, String maNv, double tongTien, boolean trangThai) {
		this.maHoaDon = maHoaDon;
		this.thoiGianXuat = thoiGianXuat;
		this.maNcc = maNcc;
		this.maNv = maNv;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}
	
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	
	
	public Date getThoiGianXuat() {
		return thoiGianXuat;
	}
	public void setThoiGianXuat(Date thoiGianXuat) {
		this.thoiGianXuat = thoiGianXuat;
	}
	public String getMaNcc() {
		return maNcc;
	}
	public void setMaNcc(String maNcc) {
		this.maNcc = maNcc;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public String getMaNv() {
		return maNv;
	}
	public void setMaNv(String maNv) {
		this.maNv = maNv;
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

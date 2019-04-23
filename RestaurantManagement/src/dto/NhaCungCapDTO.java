package dto;

public class NhaCungCapDTO {
	private String maNhaCungCap;
	private String tenNhaCungCap;
	private boolean trangThai;
	
	public NhaCungCapDTO() {
		
	}
	
	public NhaCungCapDTO(String maNhaCungCap, String tenNhaCungCap, boolean trangThai) {
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.trangThai = trangThai;
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

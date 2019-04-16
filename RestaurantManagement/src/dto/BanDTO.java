package dto;


public class BanDTO {
	private String maBan;
	private String maKhuVuc;
	private boolean trangThai;
	
	public BanDTO() {
		maBan = "tb00";
		maKhuVuc = "z0";
		trangThai = false;
	}
	
	public BanDTO(String maBan, String tableName, String maKhuVuc, boolean trangThai) {
		this.maBan = maBan;
		this.maKhuVuc = maKhuVuc;
		this.trangThai = trangThai;
	}

	public String getmaBan() {
		return maBan;
	}

	public void setmaBan(String maBan) {
		this.maBan = maBan;
	}


	public String getmaKhuVuc() {
		return maKhuVuc;
	}

	public void setmaKhuVuc(String maKhuVuc) {
		this.maKhuVuc = maKhuVuc;
	}

	public boolean istrangThai() {
		return trangThai;
	}

	public void settrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

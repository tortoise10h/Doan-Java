package bus;

import java.util.ArrayList;

import dao.ChiTietHoaDonNhapDAO;
import dto.ChiTietHoaDonNhapDTO;

public class ChiTietHoaDonNhapBUS {
	private ArrayList<ChiTietHoaDonNhapDTO> chiTietHoaDonNhapList;
	
	public ChiTietHoaDonNhapBUS() {
		chiTietHoaDonNhapList = ChiTietHoaDonNhapDAO.getDsChiTietHoaDonNhap();
	}
	
	
	public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTietHoaDonNhap() {
		return ChiTietHoaDonNhapDAO.getDsChiTietHoaDonNhap();
	}

	public ArrayList<ChiTietHoaDonNhapDTO> getChiTietHoaDonNhapList() {
		return chiTietHoaDonNhapList;
	}

	public void setChiTietHoaDonNhapList(ArrayList<ChiTietHoaDonNhapDTO> chiTietHoaDonNhapList) {
		this.chiTietHoaDonNhapList = chiTietHoaDonNhapList;
	}
	
	
	public void saveChiTietHoaDonNhap(int maHoaDonNhap, String maNguyenLieu, int gia, double soLuong, String donViTinh, int tongTien) {
		ChiTietHoaDonNhapDTO chiTietHoaDonNhap = new ChiTietHoaDonNhapDTO(maHoaDonNhap,maNguyenLieu,gia,soLuong,donViTinh,tongTien,true);
		ChiTietHoaDonNhapDAO.saveChiTietHoaDonNhap(chiTietHoaDonNhap);
	}
	
}

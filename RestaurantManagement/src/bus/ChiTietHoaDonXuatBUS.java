package bus;

import dto.ChiTietHoaDonXuatDTO;
import dto.MonTamDTO;
import dao.ChiTietHoaDonXuatDAO;

import java.util.*;

public class ChiTietHoaDonXuatBUS {
	private ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList;
	
	public ChiTietHoaDonXuatBUS() {
		chiTietHoaDonXuatList = ChiTietHoaDonXuatDAO.getDsChiTietHoaDonXuat();
	}
	
	public void saveChiTietHoaDonXuat(ArrayList<MonTamDTO> hdTamList, int maHoaDon) {
		ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList = new ArrayList<ChiTietHoaDonXuatDTO>();
		for(int i = 0; i < hdTamList.size(); i++) {
			ChiTietHoaDonXuatDTO chiTietHoaDonXuat = new ChiTietHoaDonXuatDTO();
			chiTietHoaDonXuat.setMaHoaDonXuat(maHoaDon);
			chiTietHoaDonXuat.setMaMon(hdTamList.get(i).getMon().getMaMon());
			chiTietHoaDonXuat.setDonGia(hdTamList.get(i).getMon().getGia());
			chiTietHoaDonXuat.setSoLuong(hdTamList.get(i).getSoLuong());
			chiTietHoaDonXuat.setThanhTien(hdTamList.get(i).getMon().getGia() * hdTamList.get(i).getSoLuong());
			
			chiTietHoaDonXuatList.add(chiTietHoaDonXuat);
		}
		
		ChiTietHoaDonXuatDAO.saveChiTietHoaDonXuat(chiTietHoaDonXuatList);
		
	}
	
	public void deleteBillDetail(int maHoaDonXuat) {
		ChiTietHoaDonXuatDAO.deleteExportBillDetail(maHoaDonXuat);
	}
	
	
	public ArrayList<ChiTietHoaDonXuatDTO> getDsChiTietHoaDonXuat() {
		chiTietHoaDonXuatList = ChiTietHoaDonXuatDAO.getDsChiTietHoaDonXuat();
		
		return chiTietHoaDonXuatList;
	}

	public ArrayList<ChiTietHoaDonXuatDTO> getChiTietHoaDonXuatList() {
		return chiTietHoaDonXuatList;
	}

	public void setChiTietHoaDonXuatList(ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList) {
		this.chiTietHoaDonXuatList = chiTietHoaDonXuatList;
	}
	
	
}

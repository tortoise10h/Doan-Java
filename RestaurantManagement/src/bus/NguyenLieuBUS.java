package bus;

import java.util.ArrayList;

import dao.ChiTietMonDAO;
import dao.NguyenLieuDAO;
import dto.NguyenLieuDTO;
import dto.NguyenLieuTamDTO;
import util.MysqlDatabaseAccessHelper;

public class NguyenLieuBUS {
	private ArrayList<NguyenLieuDTO> nguyenLieuList = new ArrayList<NguyenLieuDTO>();
	
	public NguyenLieuBUS() {
		nguyenLieuList = NguyenLieuDAO.getDsNguyenLieu();
	}

	public ArrayList<NguyenLieuDTO> getNguyenLieuList() {
		return nguyenLieuList;
	}

	public void setNguyenLieuList(ArrayList<NguyenLieuDTO> nguyenLieuList) {
		this.nguyenLieuList = nguyenLieuList;
	}
	
	public ArrayList<NguyenLieuDTO> getAllNguyenLieu(){
		return NguyenLieuDAO.getDsNguyenLieu();
	}
	
	public NguyenLieuDTO getNguyenLieu(String maNguyenLieu) {
		return NguyenLieuDAO.getNguyenLieu(maNguyenLieu);
	}
	
	public void updateSoluong(String maNguyenLieu, double soLuong) {
		NguyenLieuDAO.updateSoLuong(maNguyenLieu, soLuong);
	}
	
	public void updateMultipleRowSoluong(ArrayList<NguyenLieuDTO> nguyenLieuTamList) {
		NguyenLieuDAO.updateMultipleRowSoluong(nguyenLieuTamList);
	}
	
	public void decreaseSoluong(String maNguyenLieu, double saleQuantity) {
		NguyenLieuDAO.decreaseSoluong(maNguyenLieu, saleQuantity);
	}
	
	public boolean isNguyenLieuUsed(String maNguyenLieu) {
		if(ChiTietMonDAO.countRowOfNguyenLieu(maNguyenLieu) > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isNguyenLieuDuplicated(String maNguyenLieu) {
		if(NguyenLieuDAO.checkAvailableNguyenLieu(maNguyenLieu) > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addNewNguyenLieu(NguyenLieuDTO nguyenLieu) {
		NguyenLieuDAO.addNewNguyenLieu(nguyenLieu);
	}
	
	public void deleteNguyenLieu(String maNguyenLieu) {
		NguyenLieuDAO.deleteNguyenLieu(maNguyenLieu);
	}
	
	public void updateNguyenLieu(NguyenLieuDTO nguyenLieu) {
		NguyenLieuDAO.updateNguyenLieu(nguyenLieu);
	}
}

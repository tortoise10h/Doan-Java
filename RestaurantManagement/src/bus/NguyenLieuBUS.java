package bus;

import java.util.ArrayList;

import dao.NguyenLieuDAO;
import dto.NguyenLieuDTO;
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
	
	public NguyenLieuDTO getNguyenLieu(String maNguyenLieu) {
		return NguyenLieuDAO.getNguyenLieu(maNguyenLieu);
	}
	
	public void updateSoluong(String maNguyenLieu, double soLuong) {
		NguyenLieuDAO.updateSoLuong(maNguyenLieu, soLuong);
	}
	
	public void decreaseSoluong(String maNguyenLieu, double saleQuantity) {
		NguyenLieuDAO.decreaseSoluong(maNguyenLieu, saleQuantity);
	}
	
}

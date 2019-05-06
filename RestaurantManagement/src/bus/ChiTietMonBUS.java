package bus;

import java.util.*;
import dto.ChiTietMonDTO;
import dao.ChiTietMonDAO;

public class ChiTietMonBUS {
	private ArrayList<ChiTietMonDTO> chiTietMonList = new ArrayList<ChiTietMonDTO>();
	
	public ChiTietMonBUS() {
		chiTietMonList = ChiTietMonDAO.getDsChiTietMon();
	}
	
	public ArrayList<ChiTietMonDTO> getDsChiTietMon(){
		return ChiTietMonDAO.getDsChiTietMon();
	}

	public ArrayList<ChiTietMonDTO> getChiTietMonList() {
		return chiTietMonList;
	}

	public void setChiTietMonList(ArrayList<ChiTietMonDTO> chiTietMonList) {
		this.chiTietMonList = chiTietMonList;
	}
	
	public void addChiTietMonOfMon(ArrayList<ChiTietMonDTO> chiTietMonList) {
		ChiTietMonDAO.addChiTietMonOfMon(chiTietMonList);
	}
	
	public void updateMultipleChiTietMon(ArrayList<ChiTietMonDTO> chiTietMonList) {
		ChiTietMonDAO.updateMultipleChiTietMon(chiTietMonList);
	}
	
	public void deleteParamentlyChiTietMonByMaMon(String maMon) {
		ChiTietMonDAO.deleteParamentlyChiTietMonByMaMon(maMon);
	}
	
	public void deleteChiTietMonByMaMon(String maMon) {
		ChiTietMonDAO.deleteChiTietMonByMaMon(maMon);
	}
	
	public ArrayList<ChiTietMonDTO> getChiTietOfMon(String maMon){
		return ChiTietMonDAO.getChiTietOfMon(maMon);
	}
}

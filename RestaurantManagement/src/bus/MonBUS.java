package bus;

import dto.MonDTO;
import dao.MonDAO;

import java.util.*;

public class MonBUS {
	private ArrayList<MonDTO> monList = new ArrayList<MonDTO>();
	
	public MonBUS() {
		monList = MonDAO.monAll();
	}
	
	public ArrayList<MonDTO> getMonListFromDAO(){
		return MonDAO.monAll();
	}

	public ArrayList<MonDTO> getMonList() {
		return monList;
	}

	public void setMonList(ArrayList<MonDTO> monList) {
		this.monList = monList;
	}
	
	public boolean checkAvailableMon(String maMon) {
		int rowResult = MonDAO.checkAvailableMon(maMon);
		if(rowResult == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public void updateMon(MonDTO mon) {
		MonDAO.updateMon(mon);
	}
	
	public MonDTO getMon(String maMon) {
		return MonDAO.getMon(maMon);
	}
	
	public void addNewMon(MonDTO mon) {
		MonDAO.addNewMon(mon);
	}
	
	public void deleteMon(String maMon) {
		MonDAO.deleteMon(maMon);
	}
	
}

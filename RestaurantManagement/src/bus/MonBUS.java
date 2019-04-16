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
	
	
}

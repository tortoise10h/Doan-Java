package bus;

import java.util.*;

import dao.NhaCungCapDAO;
import dto.NhaCungCapDTO;

public class NhaCungCapBUS {
	private ArrayList<NhaCungCapDTO> nhaCungCapList = new ArrayList<NhaCungCapDTO>();
	
	public NhaCungCapBUS() {
		nhaCungCapList = NhaCungCapDAO.getDsNhaCungCap();
	}

	public ArrayList<NhaCungCapDTO> getNhaCungCapList() {
		return nhaCungCapList;
	}

	public void setNhaCungCapList(ArrayList<NhaCungCapDTO> nhaCungCapList) {
		this.nhaCungCapList = nhaCungCapList;
	}
	
	public NhaCungCapDTO getNhaCungCap(String maNhaCungCap) {
		return NhaCungCapDAO.getNhaCungCap(maNhaCungCap);
	}
	
}

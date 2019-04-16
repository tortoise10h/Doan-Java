package bus;

import java.util.ArrayList;
import java.util.Date;

import dto.HoaDonXuatDTO;
import dto.HoaDonTamDTO;
import dao.HoaDonXuatDAO;
import util.DateHandle;

public class HoaDonXuatBUS {
	private ArrayList<HoaDonXuatDTO> hoaDonXuatList;
	
	public HoaDonXuatBUS() {
		hoaDonXuatList = HoaDonXuatDAO.getDsHoaDonXuat();
	}
	
	public static ArrayList<HoaDonXuatDTO> getDsHoaDonXuat() {		
		return HoaDonXuatDAO.getDsHoaDonXuat();
	}
	
	public static void exportBill(HoaDonTamDTO hoaDonTam, String maBan, String maNv) {
		String gioXuatStr;
		ArrayList<String> currentTime = DateHandle.getCurrentTime();
		
		//yyyy-mm-dd hh:mm:ss
		gioXuatStr = currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5);
		HoaDonXuatDAO.saveExportBill(hoaDonTam, maBan, maNv,gioXuatStr);
	}
	
	public void deleteBill(int maHoaDon) {
		HoaDonXuatDAO.deleteExportBill(maHoaDon);
	}

	public ArrayList<HoaDonXuatDTO> getHoaDonXuatList() {
		return hoaDonXuatList;
	}

	public void setHoaDonXuatList(ArrayList<HoaDonXuatDTO> hoaDonXuatList) {
		this.hoaDonXuatList = hoaDonXuatList;
	}
	
	
}

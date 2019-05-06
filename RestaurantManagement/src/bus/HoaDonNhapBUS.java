package bus;

import dto.HoaDonNhapDTO;
import dto.HoaDonTamDTO;
import util.DateHandle;
import dao.HoaDonNhapDAO;
import dao.HoaDonXuatDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonNhapBUS {
	private ArrayList<HoaDonNhapDTO> hoaDonNhapList = new ArrayList<HoaDonNhapDTO>();
	
	public HoaDonNhapBUS() {
		hoaDonNhapList = HoaDonNhapDAO.getDsHoaDonNhap();
	}

	public ArrayList<HoaDonNhapDTO> getHoaDonNhapList() {
		return hoaDonNhapList;
	}

	public void setHoaDonNhapList(ArrayList<HoaDonNhapDTO> hoaDonNhapList) {
		this.hoaDonNhapList = hoaDonNhapList;
	}
	
	public ArrayList<HoaDonNhapDTO> getAllHoaDonNhap(){
		return HoaDonNhapDAO.getDsHoaDonNhap();
	}
	
	public void exportBill(HoaDonNhapDTO hoaDonNhap) {
		String gioXuatStr;
		ArrayList<String> currentTime = DateHandle.getCurrentTime();
		
		//yyyy-mm-dd hh:mm:ss
		gioXuatStr = currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		HoaDonNhapDAO.saveImportBill(hoaDonNhap,gioXuatStr);
	}
	
	public HoaDonNhapDTO getHoaDonNhap(int maHoaDonNhap) {
		return HoaDonNhapDAO.getHoaDonNhap(maHoaDonNhap);
	}
	
	public void deleteBill(int maHoaDon) {
		HoaDonNhapDAO.deleteImportBill(maHoaDon);
	}
}

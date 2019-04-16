package bus;

import dto.HoaDonNhapDTO;
import java.util.*;

public class HoaDonNhapBUS {
	public static ArrayList<HoaDonNhapDTO> getDsHoaDonNhap() {
		ArrayList<HoaDonNhapDTO> hdnArr = new ArrayList<HoaDonNhapDTO>();
		
		//Temporary
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		hdnArr.add(new HoaDonNhapDTO("hdn001", HoaDonNhapDTO.StringToDate("2019-04-01 17:23:00"),"gaCC","nv001",350000,true));
		
		return hdnArr;
	}
}

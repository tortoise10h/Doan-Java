package bus;

import java.util.*;

import dao.BanDAO;
import dto.BanDTO;

public class BanBUS {
	public static ArrayList<BanDTO> tableArr;
	public BanBUS() {}
	
	public static ArrayList<BanDTO> tableAll(){
		return BanDAO.tableAll();
	}
	
}

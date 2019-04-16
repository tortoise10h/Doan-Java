package dto;

import dto.MonTamDTO;
import dto.MonDTO;

import java.util.*;

public class HoaDonTamDTO {
	private ArrayList<MonTamDTO> dsMonTam = new ArrayList<MonTamDTO>();
	private Date gioVao;
	private int tongTien;
	private boolean is_billExport;
	
	
	public HoaDonTamDTO() {
		
	}
	
	public HoaDonTamDTO(ArrayList<MonTamDTO> dsMonTam, Date gioVao) {
		this.dsMonTam = dsMonTam;
		this.gioVao = gioVao;
	}

	public ArrayList<MonTamDTO> getDsMonTam() {
		return dsMonTam;
	}

	public void setDsMonTam(ArrayList<MonTamDTO> dsMonTam) {
		this.dsMonTam = dsMonTam;
	}

	public Date getGioVao() {
		return gioVao;
	}

	public void setGioVao(Date gioVao) {
		this.gioVao = gioVao;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public boolean isIs_billExport() {
		return is_billExport;
	}

	public void setIs_billExport(boolean is_billExport) {
		this.is_billExport = is_billExport;
	}
	
	
	
	
}

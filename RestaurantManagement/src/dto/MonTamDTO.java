package dto;

import dto.MonDTO;

import java.util.*;

public class MonTamDTO {
	private MonDTO mon;
	private int soLuong;
	private boolean is_process;
	private int processingQuantity;
	
	public MonTamDTO() {
		this.is_process = false;
	}
	
	public MonTamDTO(MonDTO mon, int soLuong) {
		this.mon = mon;
		this.soLuong = soLuong;
	}

	public MonDTO getMon() {
		return mon;
	}

	public void setMon(MonDTO mon) {
		this.mon = mon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public boolean isIs_process() {
		return is_process;
	}

	public void setIs_process(boolean is_process) {
		this.is_process = is_process;
	}

	public int getProcessingQuantity() {
		return processingQuantity;
	}

	public void setProcessingQuantity(int processingQuantity) {
		this.processingQuantity = processingQuantity;
	}
	
	
	
	
	
}

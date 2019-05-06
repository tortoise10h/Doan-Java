package dto;

import java.util.*;

public class NguyenLieuTamDTO {
	private NguyenLieuDTO nguyenLieu;
	private double preProcessingQuantity;
	
	public NguyenLieuTamDTO() {
		
	}
	
	public NguyenLieuTamDTO(NguyenLieuDTO nguyenLieu, double preProcessingQuantity) {
		this.nguyenLieu = nguyenLieu;
		this.preProcessingQuantity = preProcessingQuantity;
	}

	public NguyenLieuDTO getNguyenLieu() {
		return nguyenLieu;
	}

	public void setNguyenLieu(NguyenLieuDTO nguyenLieu) {
		this.nguyenLieu = nguyenLieu;
	}

	public double getPreProcessingQuantity() {
		return preProcessingQuantity;
	}

	public void setPreProcessingQuantity(double preProcessingQuantity) {
		this.preProcessingQuantity = preProcessingQuantity;
	}
	
	
}

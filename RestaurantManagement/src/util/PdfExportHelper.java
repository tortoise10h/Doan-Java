package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import dto.MonTamDTO;

public class PdfExportHelper {
	private final String coreDestination = "src/pdf-file";
	private PdfWriter writer;
	private PdfDocument pdf;
	private Document document;
	
	public PdfExportHelper() {
	
	}
	
	public void exportProcessingBill(ArrayList<MonTamDTO> monTamList, Date exportTime, String tableID) {
		//fortmat date to string to concat to file name
		SimpleDateFormat dateFormatForBill = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat dateFormatForFileName = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String dateStringForFileName = dateFormatForFileName.format(exportTime);
		String dateStringForBill = dateFormatForBill.format(exportTime);
		
//		String fileName = "D:\\Eclipse\\RestaurantManagement\\src\\pdf-file\\processing-bills\\process_" + tableID + "_" + dateStringForFileName + ".pdf";
		String fileName = "src\\pdf-file\\processing-bills\\process_" + tableID + "_" + dateStringForFileName + ".pdf";
		try {
			writer = new PdfWriter(new FileOutputStream(fileName));
			pdf = new PdfDocument(writer);
			document = new Document(pdf);
			document.setMargins(20, 20, 20, 20);
			
			//create font 
			//normal font
//			PdfFont normalFont = PdfFontFactory.createFont("D:\\Eclipse\\RestaurantManagement\\src\\font\\DejaVuSans.ttf",PdfEncodings.IDENTITY_H,true);
			PdfFont normalFont = PdfFontFactory.createFont("src\\font\\DejaVuSans.ttf",PdfEncodings.IDENTITY_H,true);
			//bold font
			PdfFont boldFont = PdfFontFactory.createFont("src\\font\\DejaVuSans-Bold.ttf",PdfEncodings.IDENTITY_H,true);
			Paragraph tableNameHeader = new Paragraph(tableID).setFont(boldFont).setFontSize(25);
			document.add(tableNameHeader);
			
			Paragraph timeExport = new Paragraph("Giờ xuất: " + dateStringForBill).setFont(normalFont);
			document.add(timeExport);
			
			//table for processing bill
			Table table = new Table(new float[] {10,3,3});
			table.setWidth(UnitValue.createPercentValue(100));
			
			//add stuff to table
			//table header
			table.addHeaderCell(new Cell().add(new Paragraph("Tên món").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Số lượng").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Đơn vị tính").setFont(boldFont)));
			
			//table content
			for(int i = 0; i < monTamList.size(); i++) {
				table.addCell(new Cell().add(new Paragraph(monTamList.get(i).getMon().getTenMon()).setFont(normalFont)));
				table.addCell(new Cell().add(new Paragraph(Integer.toString(monTamList.get(i).getProcessingQuantity())).setFont(normalFont)));
				table.addCell(new Cell().add(new Paragraph(monTamList.get(i).getMon().getDonViTinh()).setFont(normalFont)));
			}
			
			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportPayBill(ArrayList<MonTamDTO> monTamList, Date timeIn, Date exportTime, String tableID) {
		//fortmat date to string to concat to file name and display on bill
		SimpleDateFormat dateFormatForBill = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat dateFormatForFileName = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String dateStringForFileName = dateFormatForFileName.format(exportTime);
		String dateStringForBill = dateFormatForBill.format(timeIn);
		
		String fileName = "src\\pdf-file\\pay-bills\\pay_" + tableID + "_" + dateStringForFileName + ".pdf";
		try {
			writer = new PdfWriter(new FileOutputStream(fileName));
			pdf = new PdfDocument(writer);
			document = new Document(pdf);
			document.setMargins(20, 20, 20, 20);
			
			//create font 
			//normal font
			PdfFont normalFont = PdfFontFactory.createFont("src\\font\\DejaVuSans.ttf",PdfEncodings.IDENTITY_H,true);
			//bold font
			PdfFont boldFont = PdfFontFactory.createFont("src\\font\\DejaVuSans-Bold.ttf",PdfEncodings.IDENTITY_H,true);
			Paragraph tableNameHeader = new Paragraph(tableID).setFont(boldFont).setFontSize(25);
			document.add(tableNameHeader);
			
			Paragraph timeExport = new Paragraph("Giờ vào: " + dateStringForBill).setFont(normalFont);
			document.add(timeExport);
			
			//table for processing bill
			Table table = new Table(new float[] {10,1,2,3,3});
			table.setWidth(UnitValue.createPercentValue(100));
			
			//add stuff to table
			//table header
			table.addHeaderCell(new Cell().add(new Paragraph("Tên món").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Số lượng").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Đơn vị tính").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Đơn giá").setFont(boldFont)));
			table.addHeaderCell(new Cell().add(new Paragraph("Thành tiền").setFont(boldFont)));
			
			//table content
			int thanhTien;
			int tongTien = 0;
			for(int i = 0; i < monTamList.size(); i++) {
				thanhTien = 0;
				table.addCell(new Cell().add(new Paragraph(monTamList.get(i).getMon().getTenMon()).setFont(normalFont)));
				table.addCell(new Cell().add(new Paragraph(Integer.toString(monTamList.get(i).getSoLuong())).setFont(normalFont)));
				table.addCell(new Cell().add(new Paragraph(monTamList.get(i).getMon().getDonViTinh()).setFont(normalFont)));
				table.addCell(new Cell().add(new Paragraph(Integer.toString(monTamList.get(i).getMon().getGia())).setFont(normalFont)));
				thanhTien = monTamList.get(i).getSoLuong() * monTamList.get(i).getMon().getGia();
				tongTien += thanhTien;
				table.addCell(new Cell().add(new Paragraph(Integer.toString(thanhTien)).setFont(normalFont)));
			}
			document.add(table);
			
			//total price
			Paragraph totalPrice = new Paragraph("Tổng tiền: " + tongTien + "đ").setFont(normalFont).setFontSize(18);
			document.add(totalPrice);
			
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

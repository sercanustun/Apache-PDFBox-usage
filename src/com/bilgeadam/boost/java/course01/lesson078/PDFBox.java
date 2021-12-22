package com.bilgeadam.boost.java.course01.lesson078;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFBox {
	
	public static void main(String[] args) {
		try {
			createPDF();
		} catch (Exception e) {
			System.err.println("PDF İsimlendirirken hata oluştur" + e.getMessage());
		}
	}
	
	private static void createPDF() {
		PDPageContentStream content = null;
		try {
			PDDocument pdf = new PDDocument();
			PDPage page = new PDPage();
			pdf.addPage(page);
			content = new PDPageContentStream(pdf, page);
			content.beginText();
		
			content.endText();
		finally {
			if (content !=null ) {
				try {
					
				} catch (Exception e) {
					//do nothing
				}
			}
		}
	
}
}
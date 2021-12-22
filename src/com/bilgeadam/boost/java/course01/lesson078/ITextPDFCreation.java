package com.bilgeadam.boost.java.course01.lesson078;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

public class ITextPDFCreation {
	
	private static final String PDF_FILE = "C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\Boost 20210906\\Week 16\\Day 078\\iText Example.pdf";
	
	public static void main(String[] args) {
		
		try {
			createPDF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void createPDF() throws FileNotFoundException, IOException {
		Document pdf = new Document(PageSize.A4, 20, 20, 20, 20);
		try (FileOutputStream fos = new FileOutputStream(ITextPDFCreation.PDF_FILE)) {
			
		}
	}
	
}
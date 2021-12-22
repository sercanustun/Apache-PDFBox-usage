package com.bilgeadam.boost.java.course01.lesson078;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextPDFCreation {
	
	private static final String PDF_FILE = "C:\\Users\\sercu\\SERCAN\\DERSICI\\iText Example.pdf";
	private static final String LUCIDA_SANS_UNICODE = "C:\\Windows\\Fonts\\l_10646.ttf";
	private static Font font;
	
	public static void main(String[] args) {
		
		try {
			createPDF();
			createImage();
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createImage() throws BadElementException, MalformedURLException, IOException {
		String imageFileName = "C:\\Users\\sercu\\SERCAN\\DERSICI\\1.jpg";
		Image img = Image.getInstance(imageFileName);
		img.setAlignment(Image.ALIGN_LEFT);
		img.setBorder(Image.LEFT | Image.BOTTOM | Image.TOP | Image.RIGHT);
		img.setBorderColor(BaseColor.MAGENTA);
		img.setAbsolutePosition(10, 10);
		img.scalePercent(10, 10);
		img.setPaddingTop(20);
		Chunk chunk = new Chunk();
		
	}
	
	private static Font getFont() {
		if (ITextPDFCreation.font == null) {
			ITextPDFCreation.font = FontFactory.getFont(LUCIDA_SANS_UNICODE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		}
		return ITextPDFCreation.getFont();
	}
	
	private static void createPDF() throws FileNotFoundException, IOException, DocumentException {
		Document pdf = new Document(PageSize.A4, 20, 20, 20, 20);
		System.out.println("PDF created");
		
		try (FileOutputStream fos = new FileOutputStream(ITextPDFCreation.PDF_FILE)) { // içerik doldurmaca burada
																						// baþlýyor
			PdfWriter writer = PdfWriter.getInstance(pdf, fos);
			// writer.setEncryption("gizli".getBytes(), "mizli".getBytes(),
			// PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
			pdf.open();
			pdf.add(new Paragraph("bir gün okula giderken..."));
			pdf.add(new Paragraph("her þeye dikkat ederken..."));
			pdf.add(new Paragraph("bir kız çıktı karşıma..."));
			
			pdf.addAuthor("Sercan Üstün");
			pdf.addCreationDate();
			pdf.addCreator("Automated creation");
			pdf.addTitle("iText ile PDF dosyası oluşturma");
			pdf.addKeywords("pdf; java; bilgeadam;");
			pdf.close();
			writer.close();
		}
		System.out.println("PDF content written");
	}
}
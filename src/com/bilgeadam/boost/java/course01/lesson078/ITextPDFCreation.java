package com.bilgeadam.boost.java.course01.lesson078;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextPDFCreation {
	
	private static final String PDF_FILE = "C:\\Users\\sercu\\SERCAN\\DERSICI\\iText Example.pdf";
	private static final String LUCIDA_SANS_UNICODE = "C:\\Windows\\Fonts\\l_10646.ttf";
	private Font font;
	private Document pdf;
	private PdfWriter writer;
	
	public ITextPDFCreation() {
		this.pdf = new Document(PageSize.A4, 20, 20, 20, 20);
		this.font = this.getFont();
		try {
			FileOutputStream fos = new FileOutputStream(ITextPDFCreation.PDF_FILE);
			this.writer = PdfWriter.getInstance(pdf, fos);
			this.writer.setEncryption("gizli".getBytes(), "mizli".getBytes(), PdfWriter.ALLOW_PRINTING,
					PdfWriter.ENCRYPTION_AES_128);
			System.out.println("PDF created");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ITextPDFCreation pdfCreator = new ITextPDFCreation();
		try {
			pdfCreator.pdf.open();
			pdfCreator.createPDF();
			pdfCreator.pdf.newPage();
			pdfCreator.createImage();
			// pdfCreator.pdf.newPage();
			pdfCreator.createITextTablePDF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pdfCreator.pdf.close();
			pdfCreator.writer.close();
		}
	}
	
	private void createImage() throws MalformedURLException, IOException, DocumentException {
		String imageFileName = "C:\\Users\\sercu\\SERCAN\\DERSICI\\1.jpg";
		Image img = Image.getInstance(imageFileName);
		// img.setAlignment(Image.ALIGN_RIGHT);
		img.setAlignment(Image.ALIGN_CENTER);
		img.setBorder(Image.BOX);
		img.setBorderColor(BaseColor.MAGENTA);
		img.setBorderWidth(15.5f);
		img.scalePercent(10, 10);
		
		float width = img.getWidth() * 0.1f;
		// System.out.println(width + " / " + PageSize.A4.getWidth());
		float height = (float) (img.getHeight() * 0.1);
		// System.out.println(height + " / " + PageSize.A4.getHeight());
		float xCoord = (PageSize.A4.getWidth() - 40 - width) / 2;
		// System.out.println("xcoord: " + xCoord);
		float yCoord = (PageSize.A4.getHeight() - 40 - height) / 2;
		// System.out.println("ycoord: " + yCoord);
		img.setAbsolutePosition(xCoord, yCoord);
		img.setPaddingTop(100);
		pdf.add(img);
	}
	
	private void createITextTablePDF() throws Exception {
		// Rectangle small = new Rectangle(290, 100);
		Font smallfont = new Font(FontFamily.HELVETICA, 10);
		
		PdfPTable table = new PdfPTable(2);
		
		table.setTotalWidth(new float[] { 160, 120 });
		table.setLockedWidth(true);
		
		PdfContentByte cb = writer.getDirectContent();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Lütfen bir metin giriniz: ");
		String inp = sc.nextLine();
		sc.close();
		
		// first row
		PdfPCell cell = new PdfPCell(new Phrase(inp));
		cell.setFixedHeight(30);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		
		// second row
		cell = new PdfPCell(new Phrase("Some more text", smallfont));
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.BOX);
		table.addCell(cell);
		
		Barcode128 code128 = new Barcode128();
		
		code128.setCode("14785236987541");
		code128.setCodeType(Barcode128.CODE128);
		Image code128Image = code128.createImageWithBarcode(cb, null, null);
		cell = new PdfPCell(code128Image, true);
		cell.setBorder(Rectangle.BOX);
		cell.setFixedHeight(30);
		table.addCell(cell);
		
		// third row
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("and something else here", smallfont));
		cell.setBorder(Rectangle.BOX);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		pdf.add(table);
		
	}
	
	private Font getFont() {
		if (this.font == null) {
			this.font = FontFactory.getFont(LUCIDA_SANS_UNICODE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		}
		return this.font;
	}
	
	private void createPDF() throws FileNotFoundException, IOException, DocumentException {
		pdf.add(new Paragraph("bir gün okula giderken..."));
		pdf.add(new Paragraph("her þeye dikkat ederken...", this.getFont()));
		pdf.add(new Paragraph("bir kýz çýktý karþýma...", this.getFont()));
		
		pdf.addAuthor("Babür Somer");
		pdf.addCreationDate();
		pdf.addCreator("Automated creation");
		pdf.addTitle("iText ile PDF dosyasý oluþturma");
		pdf.addKeywords("pdf; java; bilgeadam;");
		System.out.println("PDF content written");
	}
}

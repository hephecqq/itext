package com.itext.test;

import java.io.FileOutputStream;

import com.geek.tutorial.itext.table.PDFTable;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TestPdfTable {

	public static void PDFTable() throws Exception {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("table_example111.pdf"));
		document.open();
		PdfPTable sub1 = new PdfPTable(1);
		sub1.addCell(new Paragraph("机械加工工序卡片"));
		
		PdfPTable sub2 = new PdfPTable(6);
		PdfPCell cellProNo = new PdfPCell(new Paragraph("产品代号"));
		PdfPCell cellItemNo = new PdfPCell(new Paragraph("零(部)件代号"));
		PdfPCell cellItemName = new PdfPCell(new Paragraph("零(部)件名称"));
		sub2.addCell(cellProNo);
		sub2.addCell(cellItemNo);
		sub2.addCell(cellItemName);
		
		// 创建PdfPTable对象
		PdfPTable table = new PdfPTable(2);
		table.addCell(sub1);
		table.addCell(sub2);
		document.add(table);
		document.close();

	}

	public static void main(String[] args) {

		try {
			 PDFTable();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

package com.itext.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class TestParagraph {
	Document document;
	@Before
	public void before() throws FileNotFoundException, DocumentException{
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("paragraph_example.pdf"));
		document.open();
	}
	@Test
	public void test(){
		
	}
}

package com.itext.test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class TestPhrase {

	Document document;
	@Before
	public void before() throws FileNotFoundException, DocumentException{
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("paragraph_example.pdf"));
		document.open();
	}

	public static final String RESULT = "results/part1/chapter01/hello_narrow.pdf";
	@Test
	public void test2() throws IOException, DocumentException{
		 /** Path to the resulting PDF file. */
	 
	 
	    /**
	     * Creates a PDF file: hello_narrow.pdf
	     * @param    args    no arguments needed
	     */
		 // step 1
    	// Using a custom page size
        Rectangle pagesize = new Rectangle(216f, 720f);
        Document document = new Document(pagesize, 36f, 72f, 108f, 180f);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
            "Hello World! Hello People! " +
            "Hello Sky! Hello Sun! Hello Moon! Hello Stars!"));
        // step 5
        document.close();
	}
	@Test
	public void test() throws DocumentException{
		Font font = new Font(Font.COURIER, 10, Font.BOLD);
		font.setColor(new Color(0x92, 0x90, 0x83));
		//创建Chunk对象
		Chunk chunk=new Chunk("test text element",font);
		chunk.setBackground(new Color(0xff, 0xe4, 0x00));
		//创建Phrase对象
		Phrase phrase = new Phrase(20, "This is initial text. "); // 1
		for (int i = 0; i < 10; i++) {
			phrase.add(chunk);
		}
		document.add(phrase);
		document.close();
		}
}

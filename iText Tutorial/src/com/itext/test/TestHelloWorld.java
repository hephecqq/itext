package com.itext.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class TestHelloWorld {

	@Test
	public void test() throws FileNotFoundException, DocumentException{
		//创建Document对象
		Document document=new Document();
		//创建PdfWriter对象,创建一个名为HelloWorldTest.pdf文件
		PdfWriter.getInstance(document, new FileOutputStream("HelloWorldTest.pdf"));
		//调用document的open方法
		document.open();
		// 调用document的add方法,添加一个段落
		document.add(new Paragraph("Test Hello World"));
		//关闭document
		document.close();
		
	}
}

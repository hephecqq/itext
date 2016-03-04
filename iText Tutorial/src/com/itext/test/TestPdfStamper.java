package com.itext.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class TestPdfStamper {

	public static void main(String[] args) throws IOException, DocumentException {
		//1.创建文件输出流
		OutputStream fos=new FileOutputStream("stamper.pdf");
		
		//2.创建PDFReader对象
		PdfReader reader=new PdfReader(new FileInputStream("HelloWorld.pdf"));
		
		//3.根据PDFReader和文件输出流创建PdfStamper模板对象
		PdfStamper stamper=new PdfStamper(reader, fos);
		//4.关闭stamper
		stamper.close();
		
		
	}
}

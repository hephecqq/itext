package com.itext.test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;

public class TestChunk_Example {

	@Test
	public void test() throws FileNotFoundException, DocumentException{
		//创建一个Document对象
		Document document=new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Testchunk_example.pdf"));
		//打开文档
		document.open();
		//创建一个Font对象
		Font font=new Font(Font.COURIER,10,Font.BOLD);
		//为字体设置颜色
		font.setColor(new Color(0x32,0x43,0x45));
		Chunk chunk=new Chunk("test text element...",font);
		//为Chunk对象设置背景颜色
		chunk.setBackground(new Color(0xff,0xe4,0x00));
		//为文档添加chunk对象
		document.add(chunk);
		//关闭document对象
		document.close();
		
		
	}
}

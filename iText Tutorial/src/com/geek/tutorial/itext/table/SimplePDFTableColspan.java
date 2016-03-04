/*
 *  iText Tutorials
 *
 * Project Info:  http://www.geek-tutorials.com/java/itext/itext_index.php
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 *
 * Created on July 20, 2007
 * @Author geek-tutorials.com
 * 
 */

package com.geek.tutorial.itext.table;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SimplePDFTableColspan {

	public SimplePDFTableColspan() throws Exception {

		// 设置文档为A4
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, new FileOutputStream("SimplePDFTableColspan1.pdf"));
		document.open();

		// 设置中文编码格式
		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(baseFont, 8, Font.ITALIC);
		PdfPTable table = new PdfPTable(6);
		Paragraph paragraph1 = new Paragraph(new String("机械加工工序卡卡片"), FontChinese);
		// 设置paragraph居中对齐
		/**
		 * 字符串参数说明如下： 1.为居中对齐 2.为右对齐 3.为左对齐 默认为左对齐
		 */
		// 未解决
		paragraph1.setAlignment(Element.ALIGN_CENTER);

		PdfPCell cell = new PdfPCell(paragraph1);
		cell.setColspan(6);
		table.addCell(cell);
		
		table.addCell(new Paragraph(new String("产品代号"), FontChinese));
		table.addCell("");
		table.addCell(new Paragraph(new String("零(部)件代号"), FontChinese));
		table.addCell("");
		table.addCell(new Paragraph(new String("零(部)件名称"), FontChinese));
		table.addCell("");

		document.add(table);
		document.close();
	}

	public static void main(String[] args) {
		try {
			SimplePDFTableColspan pdfTable = new SimplePDFTableColspan();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

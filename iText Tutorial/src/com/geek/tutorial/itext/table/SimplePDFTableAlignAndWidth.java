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

import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Element; // import Element class

public class SimplePDFTableAlignAndWidth {

	public SimplePDFTableAlignAndWidth() throws Exception{
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("SimplePDFTableAlignAndWidth.pdf"));
		document.open();
		
		float[] colsWidth = {1f, 2f}; // Code 1
		PdfPTable table = new PdfPTable(colsWidth);
		
		table.setWidthPercentage(90); // Code 2
		table.setHorizontalAlignment(Element.ALIGN_LEFT); // Code 3
		
		table.addCell("1");
		table.addCell("2");
		table.addCell("3");
		table.addCell("4");
		table.addCell("5");
		table.addCell("6");		
		
		document.add(table);		
		document.close();
	}

	public static void main(String[] args) {	
		try{
			SimplePDFTableAlignAndWidth pdfTable = new SimplePDFTableAlignAndWidth();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

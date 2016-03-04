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

package com.geek.tutorial.itext.bookmarks;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfAction;
import java.io.FileOutputStream;
import java.awt.Color;

public class Anchor {

	public Anchor()throws Exception{
		
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("anchor.pdf"));
		document.open();
		
		// Code 1
		Font font = new Font();
		font.setColor(Color.BLUE);
		font.setStyle(Font.UNDERLINE);		
		
		document.add(new Chunk("Chapter 1"));			
		document.add(new Paragraph(new Chunk("Press here to go chapter 2", font).setLocalGoto("2")));// Code 2
		document.newPage();		
				
		document.add(new Chunk("Chapter 2").setLocalDestination("2"));
		document.add(new Paragraph(new Chunk("http://www.geek-tutorials.com", font).setAnchor("http://www.geek-tutorials.com")));//Code 3
		document.add(new Paragraph(new Chunk("Open outline.pdf chapter 3", font).setRemoteGoto("outline.pdf", "3")));//Code 4
		
		document.close();
		
	}
	
	public static void main(String[] args) {
		
		try{
			Anchor anchor = new Anchor();
		}catch(Exception e){
			System.out.println(e);
		}

	}

}

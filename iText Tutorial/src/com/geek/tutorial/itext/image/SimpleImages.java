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

package com.geek.tutorial.itext.image;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.codec.GifImage;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import com.lowagie.text.pdf.codec.GifImage;
import java.io.FileOutputStream;

public class SimpleImages {
	
	public SimpleImages() throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("SimpleImages.pdf"));
		document.open();
		
		// Code 1
		document.add(new Paragraph("Simple Image"));
		com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("mouse.jpg");
		document.add(image);
		
		// Code 2
		document.add(new Paragraph("\n"+"AWT Image"));
		java.awt.Image awtImg = java.awt.Toolkit.getDefaultToolkit().createImage("square.jpg");
		com.lowagie.text.Image image2 = com.lowagie.text.Image.getInstance(awtImg, null);
		document.add(image2);				
		document.newPage();
		
		// Code 3
		document.add(new Paragraph("Multipages tiff file"));
		RandomAccessFileOrArray ra = new RandomAccessFileOrArray("multipage.tif");
		int pages = TiffImage.getNumberOfPages(ra);
		for(int i = 1; i <= pages; i++){
			document.add(TiffImage.getTiffImage(ra, i));
		}		
		document.newPage();
		
		// Code 4
		document.add(new Paragraph("Animated Gifs"));
		GifImage img = new GifImage("bee.gif");
		int frame_count = img.getFrameCount();
		for(int i = 1; i <= frame_count; i++){
			document.add(img.getImage(i));
		}
		document.close();
	}
	
	public static void main(String[] args) {
		try{
			SimpleImages simpleImages = new SimpleImages();
		}catch(Exception e){
			System.out.println(e);
		}
	}

}

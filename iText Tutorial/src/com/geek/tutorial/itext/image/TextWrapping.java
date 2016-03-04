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
import com.lowagie.text.Phrase;
import com.lowagie.text.Image;
import java.io.FileOutputStream;

public class TextWrapping {

	public TextWrapping() throws Exception {
	
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("TextWrapping.pdf"));
		document.open();

		for(int i = 0; i < 100; i++)
		{
			document.add(new Phrase("AAAA ")); // Code 1
		}
		
		com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("square.jpg");
		image.setAlignment(Image.RIGHT | Image.TEXTWRAP); // Code 2
		document.add(image);
		
		document.add(new Phrase("\n\n")); // Code 3
		for(int i = 0; i < 100; i++)
		{
			document.add(new Phrase("BBBB "));
		}
		
		document.close();
		
	}
	
	
	public static void main(String[] args) {
		try{
			TextWrapping textWrapping = new TextWrapping();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}

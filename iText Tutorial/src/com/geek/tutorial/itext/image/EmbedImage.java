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
import com.lowagie.text.Chunk;
import com.lowagie.text.Image;
import java.io.FileOutputStream;

public class EmbedImage {

	public EmbedImage() throws Exception{
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("EmbedImage.pdf"));
		document.open();
		
		// Code 1
		document.add(new Phrase("Please press "));
		document.add(new Chunk(Image.getInstance("save.gif"), 0, 0));
		document.add(new Phrase(" to save the file."));
		
		document.close();
	}

	public static void main(String[] args) {
		try{
			EmbedImage embedImage = new EmbedImage();
		}catch(Exception e){
			System.out.println(e);			
		}
	}

}

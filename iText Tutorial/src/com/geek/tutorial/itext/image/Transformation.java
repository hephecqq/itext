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
import java.io.FileOutputStream;

public class Transformation {

	public Transformation() throws Exception{
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("transformation.pdf"));
		document.open();
		
		Image img = Image.getInstance("square.jpg");
		img.setAbsolutePosition(100, 650); // Code 1
		
		img.scaleAbsolute(100, 100); // Code 2
		
		img.setRotationDegrees(40); // Code 3
		
		document.add(img);
		document.close();
		
	}
	
	public static void main(String[] args) {
		try{
			Transformation trans = new Transformation();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}

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

package com.geek.tutorial.itext.acroform;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PushbuttonField;
import com.lowagie.text.pdf.TextField;
import com.lowagie.text.Rectangle;
import java.awt.Color;
import java.io.FileOutputStream;

public class ListFieldForm {

	public ListFieldForm() throws Exception{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ListFieldForm.pdf"));
		document.open();
		
		// Code 1
		String options[] = { "PS3", "XBOX 360", "Wii", "PSP", "NDS", "GBA"};
		
		// Code 2 create drop-down list
		PdfFormField dropDown = PdfFormField.createCombo(writer, true, options, 0);
		dropDown.setWidget(new Rectangle(50, 785, 120, 800),
				PdfAnnotation.HIGHLIGHT_INVERT);
		dropDown.setFieldName("dropDownList");
		dropDown.setValueAsString("PS3");
		dropDown.setMKBorderColor(Color.BLACK);
		writer.addAnnotation(dropDown);
		
		
		// Code 3 create scrollable list
		TextField scrollableList = new TextField(writer, new Rectangle(150, 740, 250,
				800), "scrollableList");
		scrollableList.setBackgroundColor(Color.WHITE);
		scrollableList.setBorderColor(Color.BLUE);
		scrollableList.setBorderWidth(2);
		scrollableList.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
		scrollableList.setFontSize(10);
		scrollableList.setChoices(options);
		scrollableList.setChoiceSelection(0);
		writer.addAnnotation(scrollableList.getListField());		
		
		// Code 4 add function and button for showing state 
		writer.addJavaScript("function showState(){" +
				"app.alert('DropDown:'+ this.getField('dropDownList').value +'\\n'+" +
				"'Scrollable List:'+this.getField('scrollableList').value);" +
				"}");		
		PushbuttonField push = new PushbuttonField(writer, new Rectangle(
				70, 710, 140, 730), "pushAction");
		push.setBackgroundColor(Color.LIGHT_GRAY);
		push.setBorderColor(Color.GRAY);
		push.setText("Show State");
		push.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
		push.setTextColor(Color.BLACK);
		PdfFormField pushbutton = push.getField();
		pushbutton.setAction(PdfAction.javaScript("showState()",
				writer));
		writer.addAnnotation(pushbutton);		
		
		document.close();
	}

	public static void main(String[] args){
		try{
			ListFieldForm listFieldForm = new ListFieldForm();
		}catch(Exception e){
			System.out.println(e);
		}

	}

}

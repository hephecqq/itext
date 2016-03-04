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
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PushbuttonField;
import com.lowagie.text.pdf.RadioCheckField;
import com.lowagie.text.Rectangle;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

public class RadioCheckBoxForm {

	public RadioCheckBoxForm() throws Exception{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("RadioCheckBoxForm.pdf"));
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Rectangle rect;		
		
		// Code 1 create radio button
		String[] radios = { "Radio1", "Radio2", "Radio3" };
		PdfFormField radioField = PdfFormField.createRadioButton(writer, true);
		radioField.setFieldName("radioField");
		radioField.setValueAsName(radios[0]);
		for (int i = 0; i < radios.length; i++) {
			rect = new Rectangle(40, 815 - i * 30, 60, 797 - i * 30);
			addRadioButton(writer, rect, radioField, radios[i], i == 0);
			cb.beginText();
			cb.setFontAndSize(bf, 12);
			cb.showTextAligned(Element.ALIGN_LEFT, radios[i], 70, 802 - i * 30, 0);
			cb.endText();
		}
		writer.addAnnotation(radioField);		
		
		
		// Code 2 create checkbox button
		String[] options = {"Check1", "Check2", "Check3"};
		for (int i = 0; i < options.length; i++) {
			rect = new Rectangle(160, 815 - i * 30, 180, 797 - i * 30);
			addCheckbox(writer, rect, options[i]);
			cb.beginText();
			cb.setFontAndSize(bf, 12);
			cb.showTextAligned(Element.ALIGN_LEFT, options[i], 190,
					802 - i * 30, 0);
			cb.endText();
		}		
	
		
		// Code 3 add function and button for showing state 
		writer.addJavaScript("function showState(){" +
				"app.alert('Radio:'+ this.getField('radioField').value +'\\n\\n'+" +
				"'Check1:'+this.getField('Check1').value +'\\n'+" +
				"'Check2:'+this.getField('Check2').value +'\\n'+" +
				"'Check3:'+this.getField('Check3').value);" +
				"}");		
		PushbuttonField push = new PushbuttonField(writer, new Rectangle(
				80, 710, 150, 730), "pushAction");
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
	
	private static void addRadioButton(PdfWriter writer, Rectangle rect,
			PdfFormField radio, String name, boolean on) throws IOException,
			DocumentException {
		RadioCheckField check = new RadioCheckField(writer, rect, null, name);
		check.setCheckType(RadioCheckField.TYPE_CHECK);
		check.setBorderColor(Color.BLACK);
		check.setChecked(on);
		radio.addKid(check.getRadioField());
	}	
	
	private static void addCheckbox(PdfWriter writer, Rectangle rect,
			String name) throws IOException, DocumentException {
		RadioCheckField check = new RadioCheckField(writer, rect, name, "On");
		check.setCheckType(RadioCheckField.TYPE_CROSS);
		check.setBorderColor(Color.BLACK);
		writer.addAnnotation(check.getCheckField());
	}	

	public static void main(String[] args){
		try{
			RadioCheckBoxForm radioCheckForm = new RadioCheckBoxForm();
		}catch(Exception e){
			System.out.println(e);
		}

	}

}

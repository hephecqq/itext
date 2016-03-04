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
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PushbuttonField;
import com.lowagie.text.pdf.TextField;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCellEvent;
import java.awt.Color;
import java.io.FileOutputStream;

public class TextFieldForm {

	public TextFieldForm()throws Exception{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("TextFieldForm.pdf"));
		document.open();
		
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setPadding(5f); // Code 1, will only affect empty field
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cell;		
		
		// Code 2, add name TextField		
		table.addCell("Name"); 
		TextField nameField = new TextField(writer, new Rectangle(0,0,200,10), "nameField");
		nameField.setBackgroundColor(Color.WHITE);
		nameField.setBorderColor(Color.BLACK);
		nameField.setBorderWidth(1);
		nameField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
		nameField.setText("");
		nameField.setAlignment(Element.ALIGN_LEFT);
		nameField.setOptions(TextField.REQUIRED);				
		cell = new PdfPCell();
		cell.setMinimumHeight(10);
		cell.setCellEvent(new FieldCell(nameField.getTextField(), 200, writer));
		table.addCell(cell);
		
		// force upper case javascript
		writer.addJavaScript("var nameField = this.getField('nameField');" +
				"nameField.setAction('Keystroke'," +
				"'forceUpperCase()');" +
				"" +
				"function forceUpperCase(){" +
				"if(!event.willCommit)event.change = " +
				"event.change.toUpperCase();" +
				"}");
		
		
		// Code 3, add empty row
		table.addCell("");
		table.addCell("");
		
		
		// Code 4, add age TextField
		table.addCell("Age");
		TextField ageComb = new TextField(writer, new Rectangle(0, 0, 30,
				10), "ageField");
		ageComb.setBorderColor(Color.BLACK);
		ageComb.setBorderWidth(1);
		ageComb.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
		ageComb.setText("12");
		ageComb.setAlignment(Element.ALIGN_RIGHT);
		ageComb.setMaxCharacterLength(2);
		ageComb.setOptions(TextField.COMB | TextField.DO_NOT_SCROLL);
		cell = new PdfPCell();
		cell.setMinimumHeight(10);
		cell.setCellEvent(new FieldCell(ageComb.getTextField(), 30, writer));
		table.addCell(cell);
		
		// validate age javascript
		writer.addJavaScript("var ageField = this.getField('ageField');" +
				"ageField.setAction('Validate','checkAge()');" +
				"function checkAge(){" +
				"if(event.value < 12){" +
				"app.alert('Warning! Applicant\\'s age can not" +
				" be younger than 12.');" +
				"event.value = 12;" +
				"}}");		
		
		
		
		// add empty row
		table.addCell("");
		table.addCell("");
		
		
		// Code 5, add age TextField
		table.addCell("Comment");
		TextField comment = new TextField(writer, new Rectangle(0, 0,200,
				100), "commentField");
		comment.setBorderColor(Color.BLACK);
		comment.setBorderWidth(1);
		comment.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
		comment.setText("");
		comment.setOptions(TextField.MULTILINE | TextField.DO_NOT_SCROLL);
		cell = new PdfPCell();
		cell.setMinimumHeight(100);
		cell.setCellEvent(new FieldCell(comment.getTextField(), 200, writer));
		table.addCell(cell);
	
		
		// check comment characters length javascript
		writer.addJavaScript(
			"var commentField = " +
			"this.getField('commentField');" +
			"commentField" +
			".setAction('Keystroke','checkLength()');" +
			"function checkLength(){" +
			"if(!event.willCommit && " +
			"event.value.length > 100){" +
			"app.alert('Warning! Comment can not " +
			"be more than 100 characters.');" +
			"event.change = '';" +
			"}}");				
		
		
		// add empty row
		table.addCell("");
		table.addCell("");
		
		
		// Code 6, add submit button	
		PushbuttonField submitBtn = new PushbuttonField(writer,
				new Rectangle(0, 0, 35, 15), "submitPOST");
		submitBtn.setBackgroundColor(Color.GRAY);
		submitBtn.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
		submitBtn.setText("POST");
		submitBtn.setOptions(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
		PdfFormField submitField = submitBtn.getField();
		submitField.setAction(PdfAction
				.createSubmitForm(
						"http://www.geek-tutorials.com/java/itext/submit.php",
						null, PdfAction.SUBMIT_HTML_FORMAT));
		cell = new PdfPCell();
		cell.setMinimumHeight(15);
		cell.setCellEvent(new FieldCell(submitField, 35, writer));
		table.addCell(cell);
		
		
		
		// Code 7, add reset button
		PushbuttonField resetBtn = new PushbuttonField(writer,
				new Rectangle(0, 0, 35, 15), "reset");
		resetBtn.setBackgroundColor(Color.GRAY);
		resetBtn.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
		resetBtn.setText("RESET");
		resetBtn.setOptions(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
		PdfFormField resetField = resetBtn.getField();
		resetField.setAction(PdfAction.createResetForm(null, 0));
		cell = new PdfPCell();
		cell.setMinimumHeight(15);
		cell.setCellEvent(new FieldCell(resetField, 35, writer));
		table.addCell(cell);		
				
		document.add(table);
		document.close();
	}
	
	
	class FieldCell implements PdfPCellEvent{
		
		PdfFormField formField;
		PdfWriter writer;
		int width;
		
		public FieldCell(PdfFormField formField, int width, PdfWriter writer){
			this.formField = formField;
			this.width = width;
			this.writer = writer;
		}
		
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas){
			try{
				// delete cell border
				PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
				cb.reset();
				
				formField.setWidget(new Rectangle(rect.left(), 
						rect.bottom(), 
						rect.left()+width, 
						rect.top()), PdfAnnotation.HIGHLIGHT_NONE);
				
				writer.addAnnotation(formField);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		try{
			TextFieldForm textFieldForm = new TextFieldForm();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

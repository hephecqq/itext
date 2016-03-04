package com.itext.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class Main {

	   /** Path to the resulting PDF file. */
    public static final String RESULT
        = "results/part1/chapter01/hello_column.pdf";
 
    /**
     * Creates a PDF file: hello_column.pdf
     * @param args no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        // we set the compression to 0 so that we can read the PDF syntax
        //writer.setCompressionLevel(0);
        // writes something to the direct content using a convenience method
        Phrase hello = new Phrase("Hello World");
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
            hello, 36, 788, 0);
        // step 5
        document.close();
    }
}

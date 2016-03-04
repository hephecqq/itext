package com.itext.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * PdfByStamperUtils的操作类
 * 
 * @author zlj
 *
 */
public class PdfByStamperUtils {
	private PdfByStamperUtils() {
	}

	private static PdfByStamperUtils instance = null;

	static {
		instance = new PdfByStamperUtils();
	}

	public static PdfByStamperUtils getInstance() {
		if (instance == null) {
			instance = new PdfByStamperUtils();
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * 
	 * @param src
	 *            读取的pdf文件地址
	 * @param dest
	 *            创建模板pdf文件的地址
	 * @return PdfStamper对象
	 */
	public static PdfStamper createStamper(String src, String dest) {
		PdfStamper stamper = null;
		try {
			// 1.创建文件缓冲输出流
			OutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("dest")));

			// 2.创建PdfReader对象,用于与原始的pdf文件关联
			PdfReader reader = new PdfReader(new BufferedInputStream(new FileInputStream(new File(src))));

			// 3.创建PdfStamper对象创建源文件的模板pdf文档
			/**
			 * Applies extra content to the pages of a PDF document. This extra
			 * content can be all the objects allowed in PdfContentByte
			 * including pages from other Pdfs. The original PDF will keep all
			 * the interactive elements including bookmarks, links and form
			 * fields.
			 * 
			 * It is also possible to change the field values and to flatten
			 * them. New fields can be added but not flattened.
			 */
			stamper = new PdfStamper(reader, bos);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		} finally {
			try {
				stamper.close();
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		}
		return stamper;
	}

	// 加页功能实现
	public static void addPage(PdfStamper stamper, int pageIndex) {

	}

	// 在原内容之前添加内容,需要调用GetOverContent方法获取PdfContentByte对象
	/**
	 * 
	 * @param content
	 *            写入的文本内容
	 * @param x
	 *            x点坐标
	 * @param y
	 *            y点坐标
	 * @param ratation
	 *            旋转度
	 */
	public static void addOverContent(String content, float x, float y, float ratation) {
		/**
		 * public PdfContentByte getOverContent(int pageNum)
		 * 
		 * 方法说明如下： Gets a PdfContentByte to write over the page of the original
		 * document. (创建一个PdfContentByte对象,可以在原始文档的页上写入...) 参数说明如下： Parameters:
		 * pageNum - the page number where the extra of the original document
		 * 添加额外内容的页号 content is written Returns: a PdfContentByte to write over
		 * the page
		 */

		/**
		 * PdfContentByte is an object containing the user positioned text and
		 * graphic contents of a page. It knows how to apply the proper font
		 * encoding. 该对象是一个包含用户一页位置的文本段,和图像内容,它知道怎样应用合适的字体编码
		 */

		/**
		 * 参数说明如下 1.canvas:将要被写入的地方 2.alignment 3.phrase:文本内容 4.x:x点 5.y:坐标点
		 * rotation:旋转的度数
		 */
		ColumnText.showTextAligned(createStamper("", "").getOverContent(1), Element.ALIGN_CENTER, new Phrase(content),
				x, y, ratation);

	}

	// 获取原始文档的元数据
	public static void inspect(PrintWriter writer, String fileName) throws IOException {
		PdfReader pdfReader = new PdfReader(fileName);
		// 写入文件名
		writer.println(fileName);
		// 写入页号
		writer.print("Number of pages: ");
		writer.println(pdfReader.getNumberOfPages());
		// 写入获取给定页号的页大小的信息的Rectangle
		Rectangle mediabox = pdfReader.getPageSize(1);
		writer.println("Size of page1:[");
		// 写入获取的Rectangle的x的位置坐标
		// Returns the lower left x-coordinate, considering a given margin.
		writer.println(mediabox.left());
		writer.println(",");
		writer.println(mediabox.bottom());
		writer.println(",");
		writer.println();
		writer.println(mediabox.top());
		writer.println(",");
		writer.println(mediabox.right());
		writer.println("]");
		// 写入获取的ratation值
		writer.println("Ratation of page 1:");
		writer.println(pdfReader.getPageRotation(1));
		writer.println("Page size with ratation of page 1:");
		writer.println(pdfReader.getPageSizeWithRotation(1));
		// 写入获取的文件大小
		writer.println("File length:");
		writer.println(pdfReader.getFileLength());
		// 写入获取的文件是否被rebuilt
		writer.println("Is rebuilt? ");
		// Checks if the document had errors and was rebuilt.
		writer.println(pdfReader.isRebuilt());
		writer.println("is encrypted?");
		writer.println(pdfReader.isEncrypted());

		// selectPages 选择你需要的页数范围
		pdfReader.selectPages("4-9");
		// 或者使用getPageSize(int index)
		// pdfReader.getPageSize(6);

	}

	// 复制pages从已存在的PDF文档中获取
	public static void copyPages() throws IOException, DocumentException {
		// 导入pages,重用已有的pages
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,
				new BufferedOutputStream(new FileOutputStream(new File(""))));
		document.open();
		/**
		 * PdfPTable构造方法说明如下： protected PdfPTable()
		 * 
		 * PdfPTable(float[] relativeWidths) Constructs a PdfPTable with the
		 * relative column widths. PdfPTable(int numColumns) Constructs a
		 * PdfPTable with numColumns columns. PdfPTable(PdfPTable table)
		 * Constructs a copy of a PdfPTable.
		 */
		PdfPTable table = new PdfPTable(2);
		PdfReader reader = new PdfReader("");
		// 获取总共的页数
		int pages = reader.getNumberOfPages();
		// 什么PdfImportedPage实例
		PdfImportedPage importedPage;
		for (int i = 1; i <= pages; i++) {
			// 利用PdfWriter对象的getImportedPage方法获取reader中指定页数的PdfImportedPage对象
			importedPage = writer.getImportedPage(reader, i);
			// 添加到PdfpTable对象的PdfPCell对象中

			/**
			 * itext中Image对象说明： An Image is the representation of a graphic
			 * element (JPEG, PNG or GIF) that has to be inserted into the
			 * document
			 */
			table.addCell(Image.getInstance(importedPage));
			// 将表添加到Document中
			document.add(table);
			document.close();
		}

	}
}

<%@
page import="java.servlet.*, 
		javax.servlet.http.*,
		java.io.*,
		java.util.*,
		com.lowagie.text.pdf.*,
		com.lowagie.text.*"
%><%

response.setContentType("application/pdf");
Document document = new Document();
try{
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, buffer); 
	document.open();

	
	PdfPTable table = new PdfPTable(2);
	table.addCell("1");
	table.addCell("2");
	table.addCell("3");
	table.addCell("4");
	table.addCell("5");
	table.addCell("6");		

	
	document.add(table);		
	document.close(); 
	
	DataOutput dataOutput = new DataOutputStream(response.getOutputStream());
	byte[] bytes = buffer.toByteArray();
	response.setContentLength(bytes.length);
	for(int i = 0; i < bytes.length; i++)
	{
		dataOutput.writeByte(bytes[i]);
	}
	
}catch(DocumentException e){
	e.printStackTrace();
}

%>
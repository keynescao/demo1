package com.demo.tst1;

import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class WordUtils {
	
	
	/*public static void writeFile(String content,String path){
		
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		Document doc = Jsoup.parse(content);		
		try{
			File file = new File(path);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			content = doc.html();
			bw.write(content);
			System.out.println(content);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (Exception ie) { 
            	ie.printStackTrace();
            }  

		}
	}
	
	
	public static void convertWordDoc2Html(String fileName,String outPutFile) throws Exception{
		
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	
		//保存图片
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			
			@Override
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
				return "test/" + suggestedName ;
			}
		});
		
		wordToHtmlConverter.processDocument(wordDocument);
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if(pics!=null){
			for(Picture pic : pics){
				pic.writeImageContent(new FileOutputStream("d:/test/" + pic.suggestFullFileName()));
			}
		}
		
		org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResut = new StreamResult(out);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		serializer.setOutputProperty(OutputKeys.METHOD,"HTML");
		serializer.transform(domSource, streamResut);
		out.close();
		writeFile(new String(out.toByteArray()), outPutFile);
	}
	
	public static void convertWordDocX2Html(String fileName,String outPutFile) throws Exception{
		
		File f = new File(fileName);
		if (!f.exists()) {
			f.delete();
		} 
		if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
			InputStream in = new FileInputStream(f);
			XWPFDocument document = new XWPFDocument(in);

			File imageFolderFile = new File("d:/test/");
			XHTMLOptions options = XHTMLOptions.create().URIResolver(
					new FileURIResolver(imageFolderFile));
			options.setExtractor(new FileImageExtractor(imageFolderFile));

			OutputStream out = new FileOutputStream(new File(outPutFile));
			XHTMLConverter.getInstance().convert(document, out, options);
		} else {
			System.out.println("Enter only MS Office 2007+ files");
		}
		
	}
	
	
	
	public static void convertHtml2PDF(String inputFile,String outputFile)throws Exception{
		
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		String url = new File(inputFile).toURI().toURL().toString();
		renderer.setDocument(url);
		
		ITextFontResolver fontResolver = renderer.getFontResolver();  
	    fontResolver.addFont("d:/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  

		
		renderer.getSharedContext().setBaseURL("file:/D:/test");
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();		
	}*/
	
	public static void convertWord2PDF(String input, String output)throws Exception	{ 
        File inputFile = new File(input); 
        File outputFile = new File(output); 
        
        String command = "C:/Program Files (x86)/OpenOffice 4/"   
                + "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";   
        Process pro  = Runtime.getRuntime().exec(command);        
        
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100); 
        try { 
            connection.connect(); 
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(inputFile, outputFile); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){} 
            pro.destroy();
        } 
    } 
	
	public static void convertWord2PDFLinux(String input, String output)throws Exception	{ 
        File inputFile = new File(input); 
        if(!inputFile.exists()){
        	System.out.println("File " + input + " not exist");
        	return;
        }
        File outputFile = new File(output); 
        
        String command = "/opt/openoffice4/"   
                + "program/soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";   
        Process pro  = Runtime.getRuntime().exec(command);        
        
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100); 
        try { 
            connection.connect(); 
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(inputFile, outputFile); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){} 
            pro.destroy();
        } 
    } 
	
	
	
	
	
	
	public static void main(String[] args)throws Exception {
	
		String sourPath = "/root/关于2016年举行员工拓展活动的通知.docx";
		//String sourPath = "D:/Java面试的常见问题.doc";
		//String outPath = "d:/test/test.htm";
		
		//WordUtils.convertWordDoc2Html(sourPath, outPath);
		
		//WordUtils.convertWordDocX2Html(sourPath, outPath);
		
		
		//convertHtml2PDF(outPath,"d:/test/test.pdf");
		
		convertWord2PDF(sourPath,"d:/test/test.pdf");
		
		//convertWord2PDFLinux(sourPath,"/root/test.pdf");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

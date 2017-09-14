import java.io.*;
import java.net.*;
import org.apache.commons.collections.set.SynchronizedSet;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;  

public class SeaTest {
	static String a = "http://192.168.47.12/parser/parse?method_name=getrealurl&video_page_url=http://www.letv.com/ptv/vplay/";
	static String b = ".html,http://192.168.47.12/parser/parse?method_name=getrealurl&video_page_url=http://www.letv.com/ptv/vplay/";
	static String c = ".html";
   	String x ;
   	
   	public static String loadJson(String url) {
   		
   		StringBuilder json = new StringBuilder(url);	
   		try {
   			URL urlObject = new URL(url);
   			URLConnection uc = urlObject.openConnection();
   			BufferedReader in = new BufferedReader(new InputStreamReader (uc.getInputStream()));		
   		   String inputline = null;   
   		   while ((inputline = in.readLine()) != null) {	   
   			   json.append(inputline);
   			   }		
   		} catch (MalformedURLException e) {	
   		 e.printStackTrace();
   		} catch (IOException e) {	
   		 e.printStackTrace();
   		}
   		return json.toString();
   	};
   	
   	public static JSONObject httpRequest(String url, String method, String output) {
   		
   		JSONObject jsonObject = null;
   		StringBuffer buffer = new StringBuffer();
   		InputStream in = null;	
   		try {	
   			URL u = new URL(url);
   			HttpURLConnection huc = (HttpURLConnection)u.openConnection();		
   			huc.setDoInput(true);
   			huc.setDoOutput(true);
   			huc.setUseCaches(false);		
   			huc.setRequestMethod(method); 			
   			huc.connect();
   			if (output != null) {	
   				OutputStream out = huc.getOutputStream();
   				out.write(output.getBytes("utf-8"));
   				out.close();		
   			}
   			in = huc.getInputStream();
   			InputStreamReader ins = new InputStreamReader(in,"utf-8");
   			BufferedReader br = new BufferedReader (ins);		
   			String str = null;
   			while((str = br.readLine()) != null) {
   				buffer.append(str);
   			}
   			br.close();
   			ins.close();
   			in.close();
   			in = null;
   			huc.disconnect();  
   			jsonObject = JSONObject.fromObject(buffer.toString());
   		} catch (ConnectException ce) {
   			ce.printStackTrace();
   			System.out.println("Weixin server connection timed out");  
   		} catch (Exception e) {
   		  e.printStackTrace();  
          System.out.println("http request error:{}"); 		
   		}
   		return jsonObject;
   	}
   	static boolean saveText (String value, JSONObject json) {	
   		boolean savetext = false;
   		String link = json.getString(value);
   		try {
   		URL url1 = new URL(link);
   		HttpURLConnection huc1 = (HttpURLConnection)url1.openConnection();
   		huc1.setDoInput(true);
   		huc1.setDoOutput(true);
   		huc1.setRequestMethod("GET");
   		huc1.setUseCaches(false);	
   		huc1.connect();
   		InputStream in = huc1.getInputStream();	
   		File file = new File("C:\\Users\\tianye\\eclipse-workspace\\test\\m3u8.txt");
   		byte[] buffer = new byte[1024];
   		int len = 0;
   		ByteArrayOutputStream bos = new ByteArrayOutputStream();	
   		while ((len = in.read(buffer)) != -1){	
   			bos.write(buffer,0,len);
   		}
   		byte[] getData = bos.toByteArray();	
   		//FileWriter fw = new FileWriter(file);
   		FileOutputStream fos = new FileOutputStream(file);	
		fos.write(getData);
		fos.close();		
//		BufferedReader br = new BufferedReader(new InputStreamReader(huc1.getInputStream(),"utf-8"));	
//		StringBuffer sbuffer = null;
//		String s = null;
//		while((s = br.readLine()) != null) {
//			sbuffer.append(s);
//		}
//		br.close();
		huc1.disconnect();	
   		} catch(MalformedURLException me){	
   			me.printStackTrace();
   		} catch (Exception e) {
   			e.printStackTrace();	
   		}	
   		savetext = true;
   		return savetext ;
   	}
   	
   	 static boolean newFile ( String filename ) {
   		 
   		 boolean newFile = false ;
   		 File file = new File (filename);
   		 
   		 try {
   		 FileReader fr = new FileReader(file);
   		 BufferedReader br = new BufferedReader(fr); 
   		 String str;
         StringBuffer sb1 =  new StringBuffer();
         String newstr = null;
   		 while ( (str = br.readLine()) != null) {	 
   		 if (str.indexOf("ts") != -1 ) {
   				str = str.substring(str.lastIndexOf("ver_"),str.indexOf(".ts")+3);
   				newstr = "[0001] http://localhost/letv/" + str + "[0000] 5 ";
   			    sb1.append(newstr);		
   			}
   		 File newf = new File("C:\\Users\\tianye\\eclipse-workspace\\test\\test.txt");
   		 FileWriter fw = new FileWriter(newf);
   		 fw.write(sb1.toString());
   		 fw.close();		 
   		 }		
   		 br.close();
   		 } catch(IOException ioe) {	 
   			 ioe.printStackTrace();
   		 } 
   		 newFile = true;
   		 return newFile;	 
   	 }
	
	public static void main (String[] args) {
		
		String x = "29605550";
		String link = a + x + b + x+ c;
		
		String method = "GET";	
		String out= null;	
		JSONObject json = SeaTest.httpRequest(link, method, out);
		
	//	XMLSerializer xml = new XMLSerializer();	
		//String s = json.getString("value");		
	//	String XMLS= xml.write(json);	
		saveText("value", json);	
		newFile("C:\\Users\\tianye\\eclipse-workspace\\test\\m3u8.txt");	
		System.out.print("123");	
	}
};





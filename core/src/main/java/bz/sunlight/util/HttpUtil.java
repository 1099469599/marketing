package bz.sunlight.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.excel.util.ExcelWriter;

public class HttpUtil {

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	private static String URL_STR = "https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=5914ae9f978fa0ec6bca6c5f47fe328b/2cf5e0fe9925bc3167a46d3458df8db1ca1370a4.jpg";
	private static final Log log = LogFactory.getLog(ExcelWriter.class);

	public static JSONObject doGetStr(String url) throws ParseException,
			IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		// httpGet.addHeader("X-HTTP-SEQID", "123456");
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url, String outStr)
			throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}

	public static String doGet(String url, String queryString) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		// method.addRequestHeader("X-HTTP-SEQID", "123456");
		try {
			if (StringUtils.isNotBlank(queryString))
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (URIException e) {
			// log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
			e.printStackTrace();
		} catch (IOException e) {
			// log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		method.addRequestHeader("X-HTTP-SEQID", "123456");
		for (Iterator it = params.entrySet().iterator(); it.hasNext();) {

		}
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			// log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return response;
	}

	public static String getIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		String localIP = "127.0.0.1";

		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * @author lby
	 * @param uri
	 *            目标地址
	 * @return
	 */
	public static boolean sendGetRequest(String uri) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public static Integer downloadFromServer(String requestUrl,String desPath) {  
	          
	        URL url=null;
			try {
				url = new URL(requestUrl);
			} catch (MalformedURLException e) {
				
				log.error(e.getMessage());
				return BaseConstant.RESULT_CODE_FAILE;
				
			}  
	        HttpURLConnection conn=null;
			try {
				conn = (HttpURLConnection) url.openConnection();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
				return BaseConstant.RESULT_CODE_FAILE;
			}  
	        try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
				return BaseConstant.RESULT_CODE_DOWNLOAD_FAIL;
			}  
	        conn.setDoInput(true);  
	        conn.setDoOutput(true); 
	        try{
	        	if(conn.getResponseCode()==HttpStatus.SC_OK){
	        		InputStream in = conn.getInputStream();
	        		File dir = new File(desPath);  
	        		if (!dir.exists()) {  
	        			dir.mkdirs();  
	        		}  
	        		if (!desPath.endsWith("/")) {  
	        			desPath += "/";  
	        		}  
	        		String filePathUrl = conn.getURL().getFile();
	        		String fileName = filePathUrl.substring(filePathUrl.lastIndexOf("."));
	        		String uuid= UUIDUtil.getOrigUUID();
	        		desPath += (uuid+fileName);   
	        		BufferedOutputStream bos=null;
	        		bos = new BufferedOutputStream(new FileOutputStream(desPath));
	        		byte[] data = new byte[1024];  
	        		int len = -1;   
	        		while ((len = in.read(data)) != -1) {  
	        			bos.write(data,0,len); 
	        		} 
	        		bos.close();
	        		in.close();  
	        		conn.disconnect();  
	        		return BaseConstant.RESULT_CODE_SUCCESS;
	        	
	           }else{
	        	   return BaseConstant.RESULT_CODE_FAILE;
	           }
	       }catch(IOException e){
	    	   log.error(e.getMessage());
	    	   return BaseConstant.RESULT_CODE_DOWNLOAD_FAIL;
	       }
	       
	    }	
	
	
	
	
	
	public static Integer downLoad(String remoteFileName, String localFileName) {
		HttpClient client = new HttpClient();
		GetMethod get = null;
		FileOutputStream output = null;

		try {
			get = new GetMethod(URL_STR);

			int i = client.executeMethod(get);

			// if (SUCCESS == i) {
			// System.out.println("The response value of token:" +
			// get.getResponseHeader("token"));
			//
			// File storeFile = new File(localFileName);
			// output = new FileOutputStream(storeFile);
			//
			// // 得到网络资源的字节数组,并写入文件
			// output.write(get.getResponseBody());
			// } else {
			// System.out.println("DownLoad file occurs exception, the error code is :"
			// + i);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			get.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}

	public static void main(String[] args) throws ParseException, IOException {
		// JSONObject doGetStr = doGetStr("http://localhost:8080/data/data");
		String doGet = doGet("http://localhost:8080/data/data", null);
		System.out.println(doGet);
	}
}

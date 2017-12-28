package com.thinkgem.jeesite.modules.cms.utils;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.finbridge.web.manage.OperateController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class HttpUtils {

	private static final Logger LOG = LoggerFactory.getLogger(OperateController.class);
	
	/*private static CloseableHttpClient httpClient = HttpClients.createDefault();
	private static HttpContext httpContext = new BasicHttpContext();
    private static HttpClientContext context = HttpClientContext.adapt(httpContext);*/
	
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url 发送请求的 URL
	 * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		// String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty(
					"user-agent",
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
				// result += line;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭输出流、输入流出现异常！" + ex);
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url 发送请求的 URL
	 * @param json  请求参数，请求参数应该是 json 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPostJson(String url, JSONObject json) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		// String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty(
					"user-agent",
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(json);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
				// result += line;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭输出流、输入流出现异常！" + ex);
				ex.printStackTrace();
			}
		}
		return result.toString();
	}


	/**
     * 发送GET命令
     */
    //public static String sendGet(String url) {
    //    CloseableHttpResponse response = null;
    //     String content = null;
	//   try {
    //        HttpGet get = new HttpGet(url);

            //设置httpGet的头部参数信息
    //        get.setHeader("Accept", "text/html, application/xhtml+xml, */*");
    //        get.setHeader("Accept-Encoding", "gzip, deflate");
    //        get.setHeader("Accept-Language", "en-US");
    //        get.setHeader("User-Agent", "User-Agent: Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");

    //        response = httpClient.execute(get, context);

    //        HttpEntity entity = response.getEntity();
    //        content = EntityUtils.toString(entity, "gb2312");

    //        EntityUtils.consume(entity);//关闭

    //        return content;
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        if (response != null) {
    //            try {
    //                response.close();
    //            } catch (IOException e1) {
    //                e1.printStackTrace();
    //            }
    //        }
    //    }
    //    return content;
    // }

	/**
	 *
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @param contentType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String formUpload(String urlStr, Map<String, String> textMap,
									Map<String, String> fileMap,String contentType) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "-----------------12345654321-----------";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Charset", "UTF-8");
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				//LOG.debug(strBuf.toString());
				out.write(strBuf.toString().getBytes("UTF-8"));
			}
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			int responseCode = conn.getResponseCode();
			if (responseCode==200) {
				// 读取返回数据
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));

				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			}else{
				StringBuffer error = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String line1 = null;
				while ((line1=bufferedReader.readLine())!=null) {
					error.append(line1).append("\n");
				}
				res=error.toString();
				bufferedReader.close();
				bufferedReader=null;
			}

		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	/**
	 *
	 * @param urlStr
	 * @param textMap
	 * @param streamMap
	 * @param contentType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String formUpload2(String urlStr, Map<String, String> textMap,
									Map<String, InputStream> streamMap,String contentType) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "-----------------12345654321-----------";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Charset", "UTF-8");
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes("UTF-8"));
			}
			if (streamMap != null) {
				Iterator iter = streamMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					InputStream inputValue = (InputStream) entry.getValue();
					if (inputValue == null) {
						continue;
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + inputName
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(inputValue);
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			int responseCode = conn.getResponseCode();
			if (responseCode==200) {
				// 读取返回数据
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));

				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			}else{
				StringBuffer error = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String line1 = null;
				while ((line1=bufferedReader.readLine())!=null) {
					error.append(line1).append("\n");
				}
				res=error.toString();
				bufferedReader.close();
				bufferedReader=null;
			}

		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
    
    public static void main(String[] args) {
    	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx07de01f63f9da102&secret=69ae7d9389c203a425f2ca3fd44983fb";
    	
    	//String rs = HttpUtils.sendGet(url);
    	
    	//JSONObject jb = JSONObject.parseObject(rs);
		//return jb.getString("task_id");
    	
    	//System.out.println(jb.getString("access_token"));
    	
    	/*String url = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=pGNWhBtj3XIqO1YwQduwfeLmj1Ntpx3LA94ZkFAti45Ui5W8VrbGp3p78vb37IzicNBVbjC3wX2O18UvlF3niY7dzzDn9OK_fJBb2piMuBejtnGWleh6TwzJxkIeFcVpMVYdAIAXGJ&type=jsapi";
    	String rs = HttpUtils.sendGet(url);
    	System.out.println(rs);*/
    	
    	
    }

}

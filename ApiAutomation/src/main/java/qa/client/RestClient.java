package qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient
{
	
	
//	 GET METHOD without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpGet httpget = new HttpGet(url);  //hhtp get request
	CloseableHttpResponse httpresponse = httpclient.execute(httpget);
	
	return httpresponse;
	}
	
//	 GET METHOD with headers
		public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
		{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //hhtp get request
		
		for(Map.Entry<String,String> entry : headerMap.entrySet())
		{
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);
		
		return httpresponse;
		}  
		
    //POST METHOD with headers
		
		 public CloseableHttpResponse post(String url,String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException
		 {
			 CloseableHttpClient httpclient = HttpClients.createDefault();  // create a client
			 HttpPost httppost = new HttpPost(url);      // Hit the post req
			 
			 httppost.setEntity(new StringEntity(entityString));  // for payload
			  
			 //for headers
			 for(Map.Entry<String,String> entry : headerMap.entrySet()) 
				{
					httppost.addHeader(entry.getKey(),entry.getValue());
				}
			
			 CloseableHttpResponse httpresponse = httpclient.execute(httppost); 
			 return httpresponse;
		 }

}
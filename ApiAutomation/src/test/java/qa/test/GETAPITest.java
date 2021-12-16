package qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.Base.TestBase;
import qa.client.RestClient;
import qa.util.utility;

public class GETAPITest extends TestBase
{
	  TestBase testbase;
	  String apiurl;
	  String serviceurl;
	  String url;
	  RestClient restClient; 
	  CloseableHttpResponse httpresponse;
	  
	  
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException
	{
	testbase = new TestBase();	   //creating object of the base
	apiurl = prop.getProperty("URL");
	serviceurl = prop.getProperty("serviceURL");
	
	url = apiurl + serviceurl;    // main url
	System.out.println(url);
	}
	
	@Test   
 	public void GetapiTest() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();    
		httpresponse = restClient.get(url);  //hit the GET l
		
		// STATUS CODE
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode--"+statuscode );
		
		Assert.assertEquals(statuscode,Response_Statuscode_200,"Status code is not 200");
		
		String responseString = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
	   //JASON string
		JSONObject Responsejson = new JSONObject(responseString);
		System.out.println("Response JSON API--"+Responsejson);
		
		
		//perpage verifi:
		String perpageValue = utility.getValueByJPath(Responsejson,"/per_page");
		System.out.println("Value of perpage-- "+ perpageValue );
		
		Assert.assertEquals(Integer.parseInt(perpageValue),6); 

		//total verifi:
		String totalValue = utility.getValueByJPath(Responsejson,"/total");
		System.out.println("Value of perpage-- "+ totalValue );
		
		Assert.assertEquals(Integer.parseInt(totalValue),12); 
		
		//JSON Array:
		String lastName = utility.getValueByJPath(Responsejson, "/data[0]/last_name");
		String Id = utility.getValueByJPath(Responsejson, "/data[0]/id");
		String avatar  = utility.getValueByJPath(Responsejson, "/data[0]/avatar");
		String firstName = utility.getValueByJPath(Responsejson, "/data[0]/first_name");
		 
		System.out.println("Lastname--"+ lastName);
		System.out.println("Id--"+Id);
		System.out.println("Avatar--"+avatar);
		System.out.println("FirstName---"+ firstName);
		
		Assert.assertEquals(lastName,"Bluth");
		Assert.assertEquals(Integer.parseInt(Id),1);
		Assert.assertEquals(avatar,"https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(firstName,"George");
		
		//all headers  
		Header[] headerArray = httpresponse.getAllHeaders();
		
		HashMap<String,String> allheaders = new HashMap<String,String>();
		
		for(Header header : headerArray)
		{
		   allheaders.put(header.getName(),header.getValue());	
		}
		
		System.out.println("Headers Arrya--"+ allheaders);
	}
	
	
	@Test   (priority=1)
 	public void GetapiTestwithHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content_Type", "application/json");
		headerMap.put("Content-Length", "<calculated when request is sent>");
		headerMap.put("Host","<calculated when request is sent>");
		headerMap.put("User-Agent", "PostmanRuntime/7.28.4");
		httpresponse = restClient.get(url);
		
		// STATUS CODE
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode--"+statuscode );
		
		Assert.assertEquals(statuscode,Response_Statuscode_200,"Status code is not 200");
		
		//JASON string
		String responseString = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		JSONObject Responsejson = new JSONObject(responseString);
		System.out.println("Response JSON API--"+Responsejson);
		
		
		//perpage verifi:
		String perpageValue = utility.getValueByJPath(Responsejson,"/per_page");
		System.out.println("Value of perpage-- "+ perpageValue );
		
		Assert.assertEquals(Integer.parseInt(perpageValue),6); 

		//total verifi:
		String totalValue = utility.getValueByJPath(Responsejson,"/total");
		System.out.println("Value of perpage-- "+ totalValue );
		
		Assert.assertEquals(Integer.parseInt(totalValue),12); 
		
		//JSON Array:
		String lastName = utility.getValueByJPath(Responsejson, "/data[0]/last_name");
		String Id = utility.getValueByJPath(Responsejson, "/data[0]/id");
		String avatar  = utility.getValueByJPath(Responsejson, "/data[1]/avatar");
		String firstName = utility.getValueByJPath(Responsejson, "/data[0]/first_name");
		 
		System.out.println("Lastname--"+ lastName);
		System.out.println("Id--"+Id);
		System.out.println("Avatar--"+avatar);
		System.out.println("FirstName---"+ firstName);
		
		Assert.assertEquals(lastName,"Bluth");
		Assert.assertEquals(Integer.parseInt(Id),1);
		Assert.assertEquals(avatar,"https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(firstName,"George");
		
		//all headers  
		Header[] headerArray = httpresponse.getAllHeaders();
		
		HashMap<String,String> allheaders = new HashMap<String,String>();
		
		for(Header header : headerArray)
		{
		   allheaders.put(header.getName(),header.getValue());	
		}
		
		System.out.println("Headers Arrya--"+ allheaders);
	}
	
	
	  
		
	
	
	
}

package qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import qa.Base.TestBase;
import qa.client.RestClient;
import qa.data.Users;

public class POSTAPITest extends TestBase
{
	String url;
	String apiurl;
	String serviceurl;
	TestBase testbase;
	CloseableHttpResponse httpresponse;
	RestClient restClient;
	
	
   @BeforeMethod
   public void setUp()
   {
	   testbase = new TestBase();
	   apiurl =prop.getProperty("URL");
	   serviceurl = prop.getProperty("serviceURL");
	   
	   url= apiurl + serviceurl;
	   System.out.println(url);
   }
   
    @Test
    public void PostapiTest() throws ClientProtocolException, IOException
    {
    	restClient = new RestClient();

    	HashMap<String,String> headerMap = new HashMap<String,String>();
    	headerMap.put("Content-Type","application/json");
//    	headerMap.put("Content-Length","<calculated when request is sent>");
    	
    	//using jackson api
    	 ObjectMapper mapper = new ObjectMapper();
    	 Users user = new Users("morpheus","begger");
    	 
    	 //Object to JSON file
    	 
    	 mapper.writeValue(new File("/Users/Shreya/eclipse-workspace/ApiAutomation/src/main/java/qa/data/user.json"), user);
    	 
    	 //Object to JSON in string
    	String jsonString = mapper.writeValueAsString(user);
    	System.out.println("JSON String--"+jsonString);
    	
    	httpresponse = restClient.post(url,jsonString,headerMap);  //post call 
    	
    	//Status code
    	int Statuscode = httpresponse.getStatusLine().getStatusCode();
    	Assert.assertEquals(Statuscode,testbase.Response_Statuscode_201);
    	
    	//jsonString
    	
    	String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
    	JSONObject ResponseObj = new JSONObject(responseString);
    	System.out.println("Json response--"+ResponseObj);
    	
    	//jason to object
    	Users userResponseObj = mapper.readValue(responseString,Users.class);
    	System.out.println("Response--"+userResponseObj); 
    	Assert.assertTrue(user.getName().equals(userResponseObj.getName())); //verification
    	
    	Assert.assertTrue(user.getJob().equals(userResponseObj.getJob())); // verification
    	System.out.println("ID ---"+userResponseObj.getId());
    	System.out.println("CreatedAt---"+userResponseObj.getCreatedAt());
    	 
    }
}

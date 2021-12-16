package qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase
{
	public int  Response_Statuscode_200 = 200;
	public int  Response_Statuscode_201 = 201;
	public int  Response_Statuscode_500 = 500;
	public int  Response_Statuscode_400 = 400;
	public int  Response_Statuscode_401 = 401;
	public Properties prop;
	
	public TestBase()
    {
		try 
		{
			prop = new Properties();
			FileInputStream File = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/qa/config/config.properties");
			prop.load(File);
		}	
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
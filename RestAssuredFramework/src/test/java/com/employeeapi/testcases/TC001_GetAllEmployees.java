package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmployees extends TestBase{
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("********Started TC001_GetAllEmployees *********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3000);		
	}
	
	@Test
	void checkresponsebody()
	{
		logger.info("*********Checking Response Body *************");
		
		String responsebody=response.getBody().asString();
		logger.info("Response body is:" +responsebody);
		Assert.assertTrue(responsebody!=null);		
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("*********Checking Status Code *************");
		
		int statuscode=response.getStatusCode();
		logger.info("Status code is:" + statuscode);
		Assert.assertEquals(statuscode, 200);	
		
	}
	
	@Test
	void checkresponseTime()
	{
		logger.info("*********Checking response time *************");
		
		long responsetime=response.getTime();
		logger.info("responsetime is:"+ responsetime);
		
		if(responsetime>2000)
		{
			logger.warn("Responsetime is greater than 2000");
		}
		
		Assert.assertTrue(responsetime<2000);	
		
	}
	
	@Test
	void statusline()
	{
		logger.info("*********Checking status line *************");
		
		String statusline=response.getStatusLine();
		logger.info("Status line is:"+ statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");		
		
	}
	
	@Test
	void contentType()
	{
		logger.info("*********Checking content Type *************");
		
		String contentType=response.header("Content-type");
		logger.info("content type is:"+ contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
	}
	
	@Test
	void checkservertype()
	{
		logger.info("*********Checking server Type *************");
		
		String servertype=response.header("Server");
		logger.info("server type is:"+ servertype);
		Assert.assertEquals(servertype, "nginx/1.16.0");		
		
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("*********Checking content encoding *************");
		
		String contentencoding=response.header("Content-Encoding");
		logger.info("content encoding is:" + contentencoding);
		Assert.assertEquals(contentencoding, "gzip");		
		
	}
	
	@Test
	void contentlength()
	{
		logger.info("*********Checking content length *************");
		
		String contentlength=response.header("Content-length");
		logger.info("content length is :" + contentlength);
		
		if(Integer.parseInt(contentlength)<100)
			logger.warn("Content length is lesthan 100");
		
		Assert.assertTrue(Integer.parseInt(contentlength)>100);
		
	}
	
	
	void teardown()
	{
		logger.info("*********Finished TC001_GetAllEmployees  *************");
		
	}
}

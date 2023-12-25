package com.interview.go.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.interview.go.basetest.BaseTest;
import com.interview.go.constants.APIHttpStatus;
import com.interview.go.pojo.Users;
import com.interview.go.restclient.RestClient;
import com.interview.go.utils.JSONPathReader;
import com.interview.go.utils.StringUtils;

import io.restassured.response.Response;

public class UserTest extends BaseTest {
	private RestClient restClient;

	@BeforeMethod
	public void userTestSetup() {
		restClient = new RestClient(prop, baseUri);
	}

	@Test
	public void createUserTest() {
		JSONPathReader jsonReaderCreate = new JSONPathReader();
		JSONPathReader jsonReaderGet = new JSONPathReader();
		Users userResource = Users.builder().name("Mohan").email(StringUtils.mailId()).gender("Male").status("Active")
				.build();
		
		  Response response = restClient.post(GOREST_ENDPOINT, "json", userResource, true)
				 	.then().log().all()
				 		.assertThat()
				 			.statusCode(APIHttpStatus.CREATED_201.getCode())
				 				.and()
				 					.extract()
				 						.response();
		  
		 int id =  jsonReaderCreate.readJson(response, "$.id");
		 String name =  jsonReaderCreate.readJson(response, "$.name");
		 Assert.assertNotNull(id);
		 Assert.assertEquals(name, userResource.getName());
		 
		Response getResPonse = restClient.get(GOREST_ENDPOINT+"/"+id, true)
		 	.then().log().all()
		 		.assertThat()
		 			.statusCode(APIHttpStatus.OK_200.getCode())
		 				.and()
		 					.extract()
		 						.response();
		 
		int getId =  jsonReaderGet.readJson(getResPonse, "$.id");
		String getName = jsonReaderGet.readJson(getResPonse, "$.name");
		
		Assert.assertEquals(id, getId);
		Assert.assertEquals(name, getName);
		 	 		
	}
	
	@Test(enabled = true)
	public void updateUserTest() {
		JSONPathReader jsonReaderCreate = new JSONPathReader();
		JSONPathReader jsonReaderUpdate = new JSONPathReader();
		Users userResource = Users.builder().name("Dolby").email(StringUtils.mailId()).gender("Female").status("Active")
				.build();
		
		  Response response = restClient.post(GOREST_ENDPOINT, "json", userResource, true)
				 	.then().log().all()
				 		.assertThat()
				 			.statusCode(APIHttpStatus.CREATED_201.getCode())
				 				.and()
				 					.extract()
				 						.response();
		  
		 int id =  jsonReaderCreate.readJson(response, "$.id");
		 String name =  jsonReaderCreate.readJson(response, "$.name");
		 Assert.assertNotNull(id);
		 Assert.assertEquals(name, userResource.getName());
		 
		 
		 userResource.setName("Danish");
		 userResource.setStatus("inactive");
		 
		 Response updateResponse = restClient.put(GOREST_ENDPOINT+"/"+id, "json", userResource, true)
		 	.then().log().all()
		 		.assertThat()
		 			.statusCode(APIHttpStatus.OK_200.getCode())
		 				.and()
		 					.extract()
		 						.response();
		 String updatedName = jsonReaderUpdate.readJson(updateResponse, "$.name");
		 String updatedStatus = jsonReaderUpdate.readJson(updateResponse, "$.status");
		 
		 Assert.assertEquals(updatedName, userResource.getName());
		 Assert.assertEquals(updatedStatus, userResource.getStatus());
		 
		 
		 
	}

}

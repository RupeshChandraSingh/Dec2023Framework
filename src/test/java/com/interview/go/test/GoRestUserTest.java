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
import io.restassured.response.ValidatableResponse;

public class GoRestUserTest extends BaseTest {
	private StringUtils stringUtil;

	@BeforeMethod
	public void testSetup() {
		restClient = new RestClient(prop, baseUri);
	}

	@Test
	public void createUserTest() {

		stringUtil = new StringUtils();
		Users userResource = Users.builder().name("HariOm").gender("male").email(stringUtil.mailId()).status("active")
				.build();
		String requestName = userResource.getName();
		Response response = restClient.post(GOREST_ENDPOINT, "json", userResource, true)
			.then().log().all()
				.assertThat()
					.statusCode(APIHttpStatus.CREATED_201.getCode())
						.extract()
							.response();
		//String contentType = response.contentType();
		//Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
		Assert.assertTrue(response.contentType().contains("json"));
		String body = response.body().toString();
		System.out.println("=========>  "+ body);
		
		//Validation using Jsonpath.
		
		JSONPathReader jsonReader = new JSONPathReader();
		int id = jsonReader.readJson(response, "$.id");
		String responsetName = jsonReader.readJson(response, "$.name");
		Assert.assertNotNull(id);
		Assert.assertEquals(requestName, responsetName);


		
		

	}
}

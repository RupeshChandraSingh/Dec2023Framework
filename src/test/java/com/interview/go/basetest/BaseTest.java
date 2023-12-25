package com.interview.go.basetest;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.interview.go.configmanager.ConfigManager;
import com.interview.go.restclient.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;


public class BaseTest {

	public static final String GOREST_ENDPOINT = "/public/v2/users";

	protected ConfigManager configManager;
	protected RestClient restClient;
	protected Properties prop;
	protected String baseUri;

	@BeforeTest
	public void setUp() {
		RestAssured.filters(new AllureRestAssured());
		configManager = new ConfigManager();
		prop = configManager.initProperty();
		baseUri = prop.getProperty("baseUri");
		

	}
}

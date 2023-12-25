package com.interview.go.restclient;

import java.util.Map;
import java.util.Properties;

import com.interview.go.utils.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	private Properties prop;
	private String baseUri;

	private RequestSpecBuilder specBuilder;
	boolean isAuthorzationAdded = false;

	public RestClient(Properties prop, String baseUri) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseUri = baseUri;
	}

	private void addAuthorization() {
		if (!isAuthorzationAdded) {
			specBuilder.addHeader("Authorization", prop.getProperty("token"));
			isAuthorzationAdded = true;
		}
	}

	private void setContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;

		default:
			System.out.println("Please pass the valid contentType....");
			throw new APIFrameworkException("INVALIDCONTENTTYPE");
		}
	}

	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(baseUri);
		addAuthorization();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headers) {
		specBuilder.setBaseUri(baseUri);
		addAuthorization();
		if (headers != null) {
			specBuilder.addHeaders(headers);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headers, Map<String, Object> queryParam) {
		specBuilder.setBaseUri(baseUri);
		addAuthorization();
		if (headers != null) {
			specBuilder.addHeaders(headers);
		}
		if (queryParam != null) {
			specBuilder.addQueryParams(queryParam);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(String contentType, Object requestBody) {
		specBuilder.setBaseUri(baseUri);
		addAuthorization();
		setContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}

		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(String contentType, Map<String, String> headers,
			Object requestBody) {
		specBuilder.setBaseUri(baseUri);
		addAuthorization();
		setContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if (headers != null) {
			specBuilder.addHeaders(headers);
		}

		return specBuilder.build();
	}
	
	
	public Response get(String serviceUrl, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
				.when().log().all()
					.get(serviceUrl);
		}
		
		return RestAssured.given(createRequestSpec())
				.when()
					.get(serviceUrl);
		
	}
	
	public Response get(String serviceUrl, Map<String, String> headers, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers)).log().all()
				.when().log().all()
					.get(serviceUrl);
		}
		
		return RestAssured.given(createRequestSpec(headers))
				.when()
					.get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String, String> headers, Map<String, Object> queryParams, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers, queryParams)).log().all()
				.when().log().all()
					.get(serviceUrl);
		}
		
		return RestAssured.given(createRequestSpec(headers, queryParams))
				.when()
					.get(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, requestBody))
					.log().all()
						.when()
							.log().all()
								.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, requestBody))
				.when()
					.post(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
					.log().all()
						.when()
							.log().all()
								.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
				.when()
					.post(serviceUrl);
	}
	
	
	public Response put(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, requestBody))
					.log().all()
						.when()
							.log().all()
								.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, requestBody))
				.when()
					.put(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
					.log().all()
						.when()
							.log().all()
								.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
				.when()
					.put(serviceUrl);
	}
	
	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, requestBody))
					.log().all()
						.when()
							.log().all()
								.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, requestBody))
				.when()
					.patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
					.log().all()
						.when()
							.log().all()
								.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(contentType, headers, requestBody))
				.when()
					.patch(serviceUrl);
	}
	
	public Response delete(String serviceUrl, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec())
					.log().all()
						.when()
							.log().all()
								.delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec())
				.when()
					.delete(serviceUrl);
	}
	
	

}

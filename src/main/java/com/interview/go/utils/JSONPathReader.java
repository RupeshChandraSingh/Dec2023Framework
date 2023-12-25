package com.interview.go.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import io.restassured.response.Response;

public class JSONPathReader {

	public <T> T readJson(Response response, String jsonPath) {
		String jsonResponse = getResponseAsString(response);

		try {
			return JsonPath.read(jsonResponse, jsonPath);
		} catch (PathNotFoundException ex) {
			ex.printStackTrace();
			throw new APIFrameworkException(jsonPath + " path not found");
		}

	}

	public <T> List<T> readJsonList(Response response, String jsonPath) {
		String jsonResponse = getResponseAsString(response);
		try {
			return JsonPath.read(jsonResponse, jsonPath);
		} catch (PathNotFoundException ex) {
			ex.printStackTrace();
			throw new APIFrameworkException(jsonPath + " path not found");
		}
	}

	public <T> Map<String, T> readJsonMap(Response response, String jsonPath) {
		String jsonResponse = getResponseAsString(response);
		try {
			return JsonPath.read(jsonResponse, jsonPath);
		} catch (PathNotFoundException ex) {
			ex.printStackTrace();
			throw new APIFrameworkException(jsonPath + " path not found");
		}
	}

	private String getResponseAsString(Response response) {
		return response.getBody().asString();
	}
}

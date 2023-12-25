package com.interview.go.configmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.interview.go.utils.APIFrameworkException;

public class ConfigManager {

	private Properties prop;
	private FileInputStream fileInputStream;

	public Properties initProperty() {
		prop = new Properties();
		String envName = System.getenv("env");

		try {
			if (envName == null) {
				fileInputStream = new FileInputStream(
						"./src/test/resources/com.interview.go.properties/config.properties");
				prop.load(fileInputStream);
				System.out.println("Environment is not specified so running in QA environment");
			} else {
				System.out.println("Running in "+envName+" environment");
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fileInputStream = new FileInputStream(
							"./src/test/resources/com.interview.go.properties/qaconfig.properties");
					prop.load(fileInputStream);
					break;
				case "dev":
					fileInputStream = new FileInputStream(
							"./src/test/resources/com.interview.go.properties/devconfig.properties");
					prop.load(fileInputStream);
					break;
				case "prep":
					fileInputStream = new FileInputStream(
							"./src/test/resources/com.interview.go.properties/prepconfig.properties");
					prop.load(fileInputStream);
					break;

				default:
					System.out.println("Please provide valid environment");
					throw new APIFrameworkException(envName+" is invalid environment");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return prop;
	}

}

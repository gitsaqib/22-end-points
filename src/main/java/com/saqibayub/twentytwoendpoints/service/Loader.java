package com.saqibayub.twentytwoendpoints.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class Loader {

	@Autowired
    private ResourceLoader resourceLoader;
	public Map<String,String> getSampleData() {
		Map<String,String> sampleData = new HashMap<String,String>();

	    try {
	    	URI uri = resourceLoader.getResource("classpath:sampledata.properties").getURI();
	    	InputStream is = new FileInputStream(uri.getPath());
	    	sampleData = getSampleData(is);
	    	is.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return sampleData;
	}
	public Map<String,String> getSampleData(InputStream is) {
		Map<String,String> sampleData = new HashMap<String,String>();

	    try {
	        Properties properties = new Properties();
	        properties.load(is);
	        for (String property : properties.stringPropertyNames()) {
	            String value = properties.getProperty(property);
	            sampleData.put(property, value);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return sampleData;
	}
	public Map<String,String> getHeaderData() {
		Map<String,String> headerData = new HashMap<String,String>();

	    try {
	    	URI uri = resourceLoader.getResource("classpath:header.properties").getURI();
	    	InputStream is = new FileInputStream(uri.getPath());
	    	headerData = getHeaderData(is);
	        is.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return headerData;
	}
	public Map<String,String> getHeaderData(InputStream is) {
		Map<String,String> headerData = new HashMap<String,String>();

	    try {
	        Properties properties = new Properties();
	        properties.load(is);
	        for (String property : properties.stringPropertyNames()) {
	            String value = properties.getProperty(property);
	            headerData.put(property, value);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return headerData;
	}
	public List<String> getEndPointsStrings() {
		List<String> endpoints=new ArrayList<String>();
	    try {
	    	URI uri = resourceLoader.getResource("classpath:endpoints.txt").getURI();
	    	FileInputStream fis = new FileInputStream(uri.getPath());
	    	endpoints=getEndPointsStrings(fis);
	    	fis.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return endpoints;
	}
	public List<String> getEndPointsStrings(InputStream is) {
		List<String> endpoints=new ArrayList<String>();
	    endpoints=new BufferedReader(new InputStreamReader(is,
			          StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
	    return endpoints;
	}

}

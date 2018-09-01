package com.saqibayub.twentytwoendpoints.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saqibayub.twentytwoendpoints.config.DomainConfig;
import com.saqibayub.twentytwoendpoints.domain.EndPoint;
import com.saqibayub.twentytwoendpoints.domain.Result;

@Component
public class RequestProcessor {


	public Result execute(DomainConfig domainConfig,EndPoint endPoint,Map<String, String> headerData) {
		Result result = null;

		String jsonResponse = new String();

		try {
			String endpointUri =  domainConfig.getBaseURL()+ endPoint.getRequestURL();
			result = new Result(endPoint.getHttpMethod().toString(), endpointUri, endPoint.getPayload(), "", "");
			HttpRequestBase http = getHttp(endpointUri,endPoint);
			for(Entry<String,String> entry: headerData.entrySet()) {
				http.setHeader(entry.getKey(), entry.getValue());
			}
			HttpHost targetHost = new HttpHost(domainConfig.getName(), domainConfig.getPort() , domainConfig.getProtocol());
			DefaultHttpClient httpclient = new DefaultHttpClient();
			BasicHttpContext ctx = null;

			HttpResponse httpResponse = httpclient.execute(targetHost, http, ctx);
			System.out.println("request Sent");

			InputStream is= httpResponse.getEntity().getContent();
			System.out.println("Got response ");
			jsonResponse = convertInputStreamToString(is);
			System.out.println("Converting it to string "+jsonResponse);
			result.setResultDetails(jsonResponse);
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}finally {

		}

		return result;
	}
	public static String convertInputStreamToString(InputStream is) {

		String encoding = "UTF-8";
		String str = new String();

		try {
			str = org.apache.commons.io.IOUtils.toString(is, encoding);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return str;
	}

	public HttpRequestBase getHttp(String endpointUri,EndPoint endPoint) throws UnsupportedEncodingException {


//		HttpEntityEnclosingRequestBase http = null;
		HttpRequestBase http = null;
			switch (endPoint.getHttpMethod()) {
			case GET:
				http = new HttpGet(endpointUri);
				break;
			case POST: {
				HttpPost thisHttp = new HttpPost(endpointUri);
				loadPayload(thisHttp,endPoint.getPayload());
				http = thisHttp;
				break;
			}
			case PUT:{
				HttpPut thisHttp = new HttpPut(endpointUri);
				loadPayload(thisHttp,endPoint.getPayload());
				http = thisHttp;
				break;
			}
			case DELETE: {
				HttpDelete httpDelete = new HttpDelete(endpointUri);
				http = httpDelete;
				break;
			}
		}
		return http;
	}
	private void loadPayload(HttpEntityEnclosingRequestBase postPut,String payLoad) throws UnsupportedEncodingException{
		if(payLoad!=null) {
			StringEntity inputPut = new StringEntity(payLoad);
			inputPut.setContentType("application/json");
			System.out.println("set Content Type as JSON");
			postPut.setEntity(inputPut);
			System.out.println("set payload "+inputPut);
		}
	}

}

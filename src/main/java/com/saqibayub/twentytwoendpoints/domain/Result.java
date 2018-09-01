package com.saqibayub.twentytwoendpoints.domain;

public class Result {
	String httpMethod;
	String requestURL;
	String payload;
	String result;
	String resultDetails;



	public Result(String httpMethod, String requestURL, String payload,String result, String resultDetails) {
		super();
		this.httpMethod = httpMethod;
		this.requestURL = requestURL;
		this.payload = payload;
		this.result = result;
		this.resultDetails = resultDetails;
	}



	public String getHttpMethod() {
		return httpMethod;
	}



	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}



	public String getRequestURL() {
		return requestURL;
	}



	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public String getResultDetails() {
		return resultDetails;
	}



	public void setResultDetails(String resultDetails) {
		this.resultDetails = resultDetails;
	}



	public String getPayload() {
		return payload;
	}



	public void setPayload(String payload) {
		this.payload = payload;
	}

}

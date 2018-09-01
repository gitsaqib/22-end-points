package com.saqibayub.twentytwoendpoints.domain;

import org.springframework.http.HttpMethod;

public class EndPoint {

	HttpMethod httpMethod;
	String requestURL;
	String payload;

	public EndPoint(String line) {
		this.parseLine(line);
	}

	private void parseLine(String line) {
		String[] tabSplit = line.split("\t");
		String httpMethodString = tabSplit[0];
		switch (httpMethodString) {
		case "GET":
			httpMethod = HttpMethod.GET;
			break;
		case "POST":
			httpMethod = HttpMethod.POST;
			break;
		case "PUT":
			httpMethod = HttpMethod.PUT;
			break;
		case "DELETE":
			httpMethod = HttpMethod.DELETE;
			break;
		}
		this.requestURL = tabSplit[1];
		if(
//			( this.httpMethod.equals(HttpMethod.PUT))
//			&&
				tabSplit.length>2
		)
		{
			this.payload= tabSplit[2];
		}
	}
	public String toString() {
		StringBuffer toString = new StringBuffer();
		toString
		.append(httpMethod)
		.append(" ")
		.append(requestURL)
		;
		if(payload!=null) {
			toString
			.append(" ")
			.append(payload)
			;
		}
		return toString.toString();
	}


	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

/*
	public Result execute(String serviceURL,Map<String,String > headerData) {
		System.out.println("headerData : "+headerData);
		Result result = new Result(this.httpMethod.toString(), this.requestURL, this.payload, "", "");

		String endpointUri =  serviceURL+ requestURL;
		ResponseEntity<Object> response = null;
		try {
			System.out.println("End Point : "+endpointUri);
			System.out.println("this.payload : "+this.payload);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			for(Entry<String,String> entry: headerData.entrySet()) {
				headers.add(entry.getKey(), entry.getValue());
			}
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity httpEntity = new HttpEntity<>(this.payload, headers);
			response = restTemplate. exchange(endpointUri, httpMethod, httpEntity, Object.class);

			if(response!=null) {
				result.setResultDetails(response.getBody().toString());
			}
			result.setResult("Success");
		} catch (Exception e) {
			result.setResult("Failure : "+e.getMessage());
			e.printStackTrace();
		}

		return result;
	}
}
//			switch (this.httpMethod) {
//			case POST:
//				{
//					HttpHeaders headers = new HttpHeaders();
//					headers.setContentType(MediaType.APPLICATION_JSON);
//					for(Entry<String,String> entry: headerData.entrySet()) {
//						headers.add(entry.getKey(), entry.getValue());
//					}
//					RestTemplate restTemplate = new RestTemplate();
//					HttpEntity httpEntity = new HttpEntity<>(this.payload, headers);
//					response = restTemplate. exchange(endpointUri, httpMethod, httpEntity, Object.class);
//				}
//				break;
//			case POST:
//				{
//					MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//					for(Entry<String,String> entry: headerData.entrySet()) {
//						headers.add(entry.getKey(), entry.getValue());
//					}
//					RestTemplate restTemplate = new RestTemplate();
//					HttpEntity<String> request = new HttpEntity<String>(this.payload, headers);
//					response = restTemplate.postForEntity(endpointUri, request, JsonNode.class);
//				}
//				break;
//				case PUT:
//				{
//					MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//					for(Entry<String,String> entry: headerData.entrySet()) {
//						headers.add(entry.getKey(), entry.getValue());
//					}
//					RestTemplate restTemplate = new RestTemplate();
//					HttpEntity<String> request = new HttpEntity<String>(this.payload, headers);
////					response =
//					restTemplate.put(endpointUri, request, String.class);
//				}
//				break;
//
//				case DELETE:
//				{
//					MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//					for(Entry<String,String> entry: headerData.entrySet()) {
//						headers.add(entry.getKey(), entry.getValue());
//					}
//					RestTemplate restTemplate = new RestTemplate();
//					HttpEntity<String> request = new HttpEntity<String>(this.payload, headers);
//					//response =
//					restTemplate.delete(endpointUri, request, String.class);
//				}
//				break;
//			}
	*/
}
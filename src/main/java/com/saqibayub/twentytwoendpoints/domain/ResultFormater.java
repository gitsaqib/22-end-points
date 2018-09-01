package com.saqibayub.twentytwoendpoints.domain;

public class ResultFormater {

	public static void formater(Result result) {
		if(result.getPayload()!=null) {
			result.setPayload(result.getPayload().replaceAll(","," , "));
		}
		if(result.getResultDetails() !=null) {
			result.setResultDetails(result.getResultDetails().replaceAll(","," , "));
		}
	}

}

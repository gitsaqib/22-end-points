package com.saqibayub.twentytwoendpoints.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saqibayub.twentytwoendpoints.config.DomainConfig;
import com.saqibayub.twentytwoendpoints.domain.EndPoint;
import com.saqibayub.twentytwoendpoints.domain.Result;
import com.saqibayub.twentytwoendpoints.domain.ResultFormater;

@Service
public class ExecuterService {

	@Autowired
	Loader loader;

	@Autowired
	Merger merger;

	@Autowired
	RequestProcessor requestProcessor;

	@Autowired
	DomainConfig domainConfig;

	public ArrayList<Result> run() {
		System.out.println("-------- Sample Data --------");
		Map<String,String> sampleData =loader.getSampleData();
		Map<String,String> headerData =loader.getHeaderData();
		System.out.println("-------- End Points Data --------");
		List<String> rawEndPoints =loader.getEndPointsStrings();
        return run(domainConfig, headerData,rawEndPoints,sampleData);
	}

	public ArrayList<Result> run(DomainConfig domainConfig ,InputStream headerDataFile,InputStream endPointsFile,InputStream sampleDataFile) {
		Map<String,String> headerData =loader.getHeaderData(headerDataFile);
		List<String> rawEndPoints =loader.getEndPointsStrings(endPointsFile);
		Map<String,String> sampleData =loader.getSampleData(sampleDataFile);

		return run(domainConfig, headerData,rawEndPoints,sampleData);
	}
	public ArrayList<Result> run(DomainConfig domainConfig, Map<String,String> headerData,List<String> rawEndPoints,Map<String,String> sampleData)
	{
		ArrayList<Result> results =new ArrayList<Result>();
		List<String> runableEndPoints =new ArrayList<String>();
		for(String endPoint : rawEndPoints){
			System.out.println("For Points : "+endPoint);
			if(endPoint.startsWith("#")) {
				continue;
			}
			List<String> mergeFields= merger.findMergeFiends(endPoint);
			String runableEndPoint = merger.repaceMergeFields(endPoint,mergeFields,sampleData);
			runableEndPoints.add(runableEndPoint);
		}

		runableEndPoints.forEach(endPointString->{
			System.out.println("Execute "+endPointString);
			EndPoint endPoint = new EndPoint( endPointString);
			Result result = requestProcessor.execute(domainConfig,endPoint,headerData);
			ResultFormater.formater(result);
			results.add(result);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		return results;
	}
}
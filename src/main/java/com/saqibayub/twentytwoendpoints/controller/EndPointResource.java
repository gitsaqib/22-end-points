package com.saqibayub.twentytwoendpoints.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saqibayub.twentytwoendpoints.config.DomainConfig;
import com.saqibayub.twentytwoendpoints.domain.Result;
import com.saqibayub.twentytwoendpoints.service.ExecuterService;

@Controller
public class EndPointResource {

	@Autowired
	ExecuterService executerService;

	@GetMapping(path="/")
	public String homeView(Model model) {

		//ArrayList<Result> results = executerService.run();

        //model.addAttribute("results", results);
		DomainConfig domainConfig = null;
		domainConfig = new DomainConfig("", "", 8080, "");
		model.addAttribute("domainConfig",domainConfig);

		return "index";
	}
	@PostMapping(path="/run")
	public String run(
			Model model,
			@RequestParam("name")
			String name,
			@RequestParam("protocol")
			String protocol,
			@RequestParam("port")
			Integer port,
			@RequestParam("serviceName")
			String serviceName,
			@RequestParam("headerDataFile")
			MultipartFile headerDataFile,
			@RequestParam("endPointsFile")
			MultipartFile endPointsFile,
			@RequestParam("sampleDataFile")
			MultipartFile sampleDataFile
			) {
		String nextView="run";
		DomainConfig domainConfig = null;
		try {
			domainConfig = new DomainConfig(name, protocol, port, serviceName);
			model.addAttribute("domainConfig", domainConfig);
			ArrayList<Result> results = executerService.run(domainConfig,headerDataFile.getInputStream(),endPointsFile.getInputStream(),sampleDataFile.getInputStream());
			model.addAttribute("results", results);
		} catch (IOException e) {
			e.printStackTrace();
			nextView="index";
		}

		if(model!=null) {
			model.addAttribute("domainConfig",domainConfig);
		}
		return nextView;
	}
	@GetMapping(path="/check")
	public String runEndPoints(Model model) {

		ArrayList<Result> results = executerService.run();

		model.addAttribute("results", results);

		return "runmyendpoints";
	}
	@GetMapping(path="/index2")
	public String index2(Model model) {

		//ArrayList<Result> results = executerService.run();

        //model.addAttribute("results", results);

		return "index2";
	}
}
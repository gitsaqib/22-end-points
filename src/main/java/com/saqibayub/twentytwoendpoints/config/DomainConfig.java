package com.saqibayub.twentytwoendpoints.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "domain")
public class DomainConfig {

	private String name;
	private String protocol;
	private int port;
	private String servicename;

	public DomainConfig() {
		super();
	}
	public DomainConfig(String name, String protocol, int port, String servicename) {
		this();
		this.name = name;
		this.protocol = protocol;
		this.port = port;
		this.servicename = servicename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getBaseURL() {
		return protocol+"://"+name+":"+port+((servicename!=null && servicename.length()>0)?("/"+servicename):"");
	}
}

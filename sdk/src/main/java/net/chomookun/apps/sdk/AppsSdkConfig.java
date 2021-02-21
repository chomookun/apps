package net.chomookun.apps.sdk;

import java.util.Map;

import com.zaxxer.hikari.HikariConfig;

import lombok.Data;

@Data
public class AppsSdkConfig {
	
	private Map<String,HikariConfig> dataSources;

}

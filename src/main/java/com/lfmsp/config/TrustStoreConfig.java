package com.lfmsp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "truststore")
public class TrustStoreConfig {

    private String path = System.getProperty("java.home") +
            (System.getProperty("os.name").toLowerCase().contains("windows") ? "\\lib\\security\\cacerts" : "/lib/security/cacerts");

   private String password = "changeit";

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

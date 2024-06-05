package com.trevis.backend.challenge;

import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.context.annotation.Configuration;

import com.trevis.backend.challenge.services.CPFValidator;
import com.trevis.backend.challenge.services.CityValidator;
import com.trevis.backend.challenge.services.CollatzFunction;
import com.trevis.backend.challenge.impl.DefaultCPFValidator;
import com.trevis.backend.challenge.impl.ViaCEPCityValidator;
import com.trevis.backend.challenge.impl.DefaultCollatzFunction;

@Configuration
public class DependenciesConfiguration {
    @Bean
    @Scope("singleton")
    public CollatzFunction collatzFunction() {
        return new DefaultCollatzFunction();
    }

    @Bean
    @Scope("singleton")
    public static RestTemplate restTemplate() {

        final String username = "disrct";
        final String password = "etstech31415";
        final String proxyUrl = "http://rb-proxy-ca1.bosch.com";
        final int port = 8080;

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            new AuthScope(proxyUrl, port), 
            new UsernamePasswordCredentials(username, password)
        );

        HttpHost myProxy = new HttpHost(proxyUrl, port);
        HttpClientBuilder clientBuilder = HttpClientBuilder
            .create()
            .setProxy(myProxy)
            .setDefaultCredentialsProvider(credentialsProvider)
            .disableCookieManagement();

        CloseableHttpClient client = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);

        return new RestTemplate(factory);
    }

    @Bean
    @Scope("singleton")
    public static CityValidator cityValidator() {
        return new ViaCEPCityValidator();
    }

    @Bean
    @Scope("singleton")
    public static CPFValidator cpfValidator() {
        return new DefaultCPFValidator();
    }
}
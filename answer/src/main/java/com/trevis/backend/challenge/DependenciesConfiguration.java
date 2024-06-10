package com.trevis.backend.challenge;

import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.InetSocketAddress;

import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import com.trevis.backend.challenge.services.CPFValidator;
import com.trevis.backend.challenge.services.SearchCityService;
import com.trevis.backend.challenge.services.UserAuth;
import com.trevis.backend.challenge.services.CityValidator;
import com.trevis.backend.challenge.services.CollatzFunction;
import com.trevis.backend.challenge.services.MailValidator;
import com.trevis.backend.challenge.services.PasswordValidator;

import com.trevis.backend.challenge.impl.DefaultCPFValidator;
import com.trevis.backend.challenge.impl.ViaCEPCityValidator;
import com.trevis.backend.challenge.impl.DefaultCollatzFunction;
import com.trevis.backend.challenge.impl.DefaultMailValidator;
import com.trevis.backend.challenge.impl.DefaultPasswordValidator;
import com.trevis.backend.challenge.impl.DefaultUserAuth;
import com.trevis.backend.challenge.impl.JPASearchCityService;

@Configuration
public class DependenciesConfiguration {

    @Bean
    @Scope("singleton")
    public UserAuth userAuth() {
        return new DefaultUserAuth();
    }

    @Bean
    @Scope("singleton")
    public PasswordValidator passwordValidator() {
        return new DefaultPasswordValidator();
    }

    @Bean
    @Scope("singleton")
    public MailValidator mailValidator() {
        return new DefaultMailValidator();
    }

    @Bean
    @Scope("singleton")
    public SearchCityService cityRepository() {
        return new JPASearchCityService();
    }
    @Bean
    @Scope("singleton")
    public CollatzFunction collatzFunction() {
        return new DefaultCollatzFunction();
    }

    @Bean
    @Scope("singleton")
    public static RestTemplate restTemplate() {

        final String proxyUrl = "http://rb-proxy-ca1.bosch.com";
        final int port = 8080;
        
        Proxy proxy = new Proxy(Type.HTTP,
            new InetSocketAddress(proxyUrl, port)
        );
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(proxy);

        RestTemplate rest = new RestTemplate(factory);
        
        return rest;
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
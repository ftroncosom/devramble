
package com.devramble.http.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@SpringBootApplication
public class HttpClientCache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HttpClientCache.class, args);
    }
    
    @Bean
    public FilterRegistrationBean basicAuthFilterRegistration() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(basicAUthFilter());
        filterBean.addUrlPatterns("/resource/basic");
        filterBean.setOrder(1);
        
        return filterBean;
    }
    
    @Bean
    public FilterRegistrationBean digestAuthFilterReg() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(digestAuthFilter());
        filterBean.addUrlPatterns("/resource/digest");
        filterBean.setOrder(1);
        
        return filterBean;
    }
    
    @Bean
    public BasicAuthenticationFilter basicAUthFilter() {
        return new BasicAuthenticationFilter();
    }
    
    @Bean
    public DigestAuthFilter digestAuthFilter() {
        return new DigestAuthFilter();
    }
    
}

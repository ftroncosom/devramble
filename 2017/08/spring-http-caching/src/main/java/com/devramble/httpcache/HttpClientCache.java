
package com.devramble.httpcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@SpringBootApplication
@EnableCaching
public class HttpClientCache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HttpClientCache.class, args);
    }
    
    @Bean
    public FilterRegistrationBean cacheFilterRegistration() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(clientCacheFilter());
        filterBean.addUrlPatterns("/person/*");
        filterBean.setOrder(1);
        
        return filterBean;
    }
    
    @Bean
    public ClientCacheFilter clientCacheFilter() {
        return new ClientCacheFilter();
    }
    
}

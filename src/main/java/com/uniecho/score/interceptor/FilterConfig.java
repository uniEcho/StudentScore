package com.uniecho.score.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by uni_E
 * @classname FilterConfig
 * @description TODO
 */
@Configuration
public class FilterConfig {
    @Bean
        public FilterRegistrationBean registerFilter() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new LoginFilter());
            registration.addUrlPatterns("/*");
            registration.setName("loginFilter");
            registration.setOrder(1);
            return registration;
        }

}

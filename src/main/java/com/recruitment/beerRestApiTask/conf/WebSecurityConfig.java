///*
//package com.recruitment.beerRestApiTask.conf;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests().antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("/h2-console/**").permitAll();
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**")
//                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH","OPTIONS")
//                    .allowedOrigins("http://localhost:4200")
//                    .allowedHeaders("Access-Control-Allow-Origin","Content-Type")
//                    .allowCredentials(false).maxAge(3600);
//    }
//
//}
//*/

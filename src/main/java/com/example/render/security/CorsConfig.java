package com.example.render.security;


import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class CorsConfig {
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//        	HttpServletResponse httpResponse = (HttpServletResponse) response;
//	        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//	        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
//	        httpResponse.setHeader("Access-Control-Expose-Headers", "custom-header1, custom-header2");
//        	httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
//	        httpResponse.setHeader("Access-Control-Max-Age", "4800");
//	        System.out.println("---CORS Configuration Completed---");
//	        chain.doFilter(request, response);
//	}
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//	@Override
//	public void destroy() {
//	}
}



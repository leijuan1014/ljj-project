package com.xiss.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiss.util.properties.GlobalProperties;

/** 
* @author leijj
* @since  2017年5月5日 上午9:20:05 
*/
public class CORSFilter implements Filter{
    public void doFilter2(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = (String) servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort();
        System.out.println("========curOrigin=" + origin);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest, servletResponse);
    }
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String curOrigin = request.getHeader("Origin");
        List<String> domain = new ArrayList<String>();
        domain.add(GlobalProperties.getFilterDomain());
        System.out.println("========curOrigin=" + curOrigin);
        response.setHeader("Access-Control-Allow-Origin", GlobalProperties.getFilterDomain());
        if (curOrigin != null) {
        		response.setHeader("Access-Control-Allow-Origin", curOrigin);
            /*for (int i = 0; i < domain.size(); i++) {
                if (curOrigin.equals(domain.get(i))) {
                    response.setHeader("Access-Control-Allow-Origin", curOrigin);
                    break;
                }
            }*/
        } else { // 对于无来源的请求(比如在浏览器地址栏直接输入请求的)，那就只允许我们自己的机器可以吧
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        // response.setHeader("Access-Control-Allow-Origin", "http://api.7bcapital.com");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Type", "application/json");
        chain.doFilter(req, res);
    }
	public void doFilter1(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	    HttpServletResponse response = (HttpServletResponse) res;
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
	    response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	    chain.doFilter(req, res);
	}
	public void init(FilterConfig filterConfig) {}
	public void destroy() {}
}

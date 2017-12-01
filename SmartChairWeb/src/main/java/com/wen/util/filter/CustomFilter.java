package com.wen.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义filter
 * 
 * @author wujing
 */
@WebFilter(urlPatterns="/*")
public class CustomFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filter");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("do filter");
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("destroy filter");
	}

}

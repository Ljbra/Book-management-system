package cn.edu.chinese;

import java.io.*;

import javax.servlet.Filter; 

import javax.servlet.FilterChain; 

import javax.servlet.FilterConfig; 

import javax.servlet.ServletException; 

import javax.servlet.ServletRequest; 

import javax.servlet.ServletResponse; 

public class SetCharacterEncodingFilter implements Filter {

protected String encoding = null; 

public void destroy(){ 

this.encoding = null; 

} 

public void init(FilterConfig filterConfig) throws ServletException

{

 this.encoding = filterConfig.getInitParameter("encoding");

}

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException

{

request.setCharacterEncoding(encoding);

response.setContentType("text/html;charset="+encoding);

chain.doFilter(request, response);

}

}

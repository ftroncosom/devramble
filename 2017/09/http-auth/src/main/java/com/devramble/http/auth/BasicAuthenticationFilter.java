
package com.devramble.http.auth;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;


public class BasicAuthenticationFilter  implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        // check for authorization header
        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=Authentication");
            return ;
        } else {
            // value should be in form Basic xxxxxxxx
            String[] parts = authHeader.split("\\s");
            if(parts.length != 2) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            byte[] binary = Base64.getDecoder().decode(parts[1]);
            String authValue = new String(binary);
            if(!authValue.equals("user:pass")) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=Authentication realm");
                return;
            }
            
        }
        
        fc.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

}

package com.devramble.httpcache;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class ClientCacheFilter implements Filter {

    @Autowired
    private PersonDigestService digestService;

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        if (hreq.getHeader(HttpHeaders.IF_NONE_MATCH) != null) {
            String ifNoneMatch = hreq.getHeader(HttpHeaders.IF_NONE_MATCH);
            String etag = digestService.getEtag(((HttpServletRequest) request).getRequestURI());

            if (ifNoneMatch.equals(etag)) {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }
        } else {
            String uri = hreq.getRequestURI();
            String etag = digestService.getEtag(uri);
            if (etag != null) {
                ((HttpServletResponse) response).setHeader(HttpHeaders.ETAG, etag);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}

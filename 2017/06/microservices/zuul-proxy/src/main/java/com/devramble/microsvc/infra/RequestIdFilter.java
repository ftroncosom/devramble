
package com.devramble.microsvc.infra;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class RequestIdFilter extends ZuulFilter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("X-RequestId", UUID.randomUUID().toString());
        LOGGER.info("Running request ID filter");
        
        return null;
    }

}



package com.devramble.microsvc.user;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@Repository
public class SlowService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SlowService.class);

        
    @Cacheable ("time")
    public String getSomeValue() throws Exception {
        LOGGER.info("Getting current time..");
        Thread.sleep(2000);
        StringBuilder sb = new StringBuilder("time: ");
        sb.append(new Date());
        
        LOGGER.info("Current time: {}", sb.toString());
        return sb.toString();
    }
    
    @CachePut("time")
    public String update() {
        StringBuilder sb = new StringBuilder("time: ");
        sb.append(new Date());
        
        return sb.toString();
    }
    
}

/*
 * The MIT License
 *
 * Copyright 2016 Vladimir Djurovic <vdjurovic@vektorsoft.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.vektorsoft.ids;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class IDGenerator {
    
    private static final int START_YEAR = 2016;
    
    private static final Random rand = new Random();
    

    public static void main(String[] args) {
        
        // number of IDs to generate
        int numIds = 10000;
        
        // generate IDs
        long startTime = System.currentTimeMillis();
        
        String[] ids = new String[numIds];
        for(int i = 0;i < numIds;i++){
            ids[i] = generateId();
        }
        long endTime = System.currentTimeMillis();
        
        long time = (endTime - startTime);
        System.out.println("Time elapsed: " + time);
        
        Arrays.sort(ids);
        // try to find duplicates
        int count = 0;
        String prev = ids[0];
        for(int i = 1;i < numIds;i++) {
            if(prev.equals(ids[i])){
                System.out.println("found duplicate: " + ids[i]);
                count++;
            }
            prev = ids[i];
        }
        System.out.println("Duplicates: " + count);
    }
    
    public static String generateId(){
        StringBuilder sb = new StringBuilder();
        // number of seconds since the start of current year
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        Year current = Year.now(ZoneId.of("UTC"));
        ZonedDateTime start = ZonedDateTime.of(LocalDateTime.of(current.getValue(), Month.JANUARY, 1, 0, 0, 0), ZoneId.of("UTC"));
        Duration duration = Duration.between(start, now);
        long seconds = duration.getSeconds();
        
        // year of system running
        int year = current.getValue() - START_YEAR;
        // random part
        int random = rand.nextInt(0xFFFFFFF);
        
        // create identifier
        sb.append(Base62.encode(year, 2)); // 2 characters for year part
        sb.append(Base62.encode((int)seconds, 5)); // 5 characters for seconds timestamp
        sb.append(Base62.encode(random, 5)); // 5 characters for random part
        
        return sb.toString();
    }
}

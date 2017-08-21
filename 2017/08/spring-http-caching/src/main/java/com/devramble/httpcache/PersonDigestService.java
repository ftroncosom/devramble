package com.devramble.httpcache;

import com.devramble.httpcache.model.Person;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@Service
public class PersonDigestService {

    private Map<String, String> etagMap = new ConcurrentHashMap<>();

    public void calculateEtag(String uri, Person person) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(person.getId()).append(person.getFirstname()).append(person.getLastname());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] output = md.digest(sb.toString().getBytes());

        String result = "";
        for (int i = 0; i < output.length; i++) {
            result += Integer.toString((output[i] & 0xff) + 0x100, 16).substring(1);
        }
        etagMap.put(uri, result);
    }
    
    public void removeEtag(String uri) {
        etagMap.remove(uri);
    }
    
    public String getEtag(String uri) {
        return etagMap.get(uri);
    }
}

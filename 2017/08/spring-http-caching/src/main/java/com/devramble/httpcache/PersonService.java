package com.devramble.httpcache;

import com.devramble.httpcache.model.Person;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@Service
public class PersonService {

    private final Map<Integer, Person> personMap = new HashMap<>();

    @Cacheable(cacheNames = "persons", key = "#id")
    public Person getPerson(int id) throws Exception {
        Thread.sleep(3000); // simulate latency
        Person person = null;
        if (personMap.containsKey(id)) {
            person = personMap.get(id);
        } else {
            person = new Person();
            person.setId(id);
            person.setFirstname(RandomStringUtils.randomAlphanumeric(10));
            person.setLastname(RandomStringUtils.randomAlphanumeric(15));

            personMap.put(id, person);
        }

        return person;
    }

    @CacheEvict(cacheNames = "persons", key = "#person.id")
    public void updatePerson(Person person) {
        personMap.put(person.getId(), person);
    }
}

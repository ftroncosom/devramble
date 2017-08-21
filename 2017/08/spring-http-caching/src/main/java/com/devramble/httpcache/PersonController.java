

package com.devramble.httpcache;

import com.devramble.httpcache.model.Person;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@RestController
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonDigestService digestService;
    

    @RequestMapping(method = RequestMethod.GET, value = "/person/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person getPersonById(@PathVariable(name = "id") int id, HttpServletRequest request) throws Exception {
        Person person =  personService.getPerson(id);
        digestService.calculateEtag(request.getRequestURI(), person);
        return person;
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/person", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updatePerson(@RequestBody Person person, HttpServletRequest request) {
        personService.updatePerson(person);
        digestService.removeEtag(request.getRequestURI());
    }
}

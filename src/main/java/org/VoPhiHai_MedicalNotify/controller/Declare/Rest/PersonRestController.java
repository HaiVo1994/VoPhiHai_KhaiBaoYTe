package org.VoPhiHai_MedicalNotify.controller.Declare.Rest;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/person")
public class PersonRestController {
    @Autowired
    private PersonService personService;
    @RequestMapping(value = "/check/{passport}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonObject> checkPassport(@PathVariable String passport){
        Person person = personService.findByLegalDocument(passport);
        if (person!=null){
            JsonObject personId = new JsonObject();
            personId.put("personId", person.getLegalDocument());
            return new ResponseEntity<>(personId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

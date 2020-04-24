package org.VoPhiHai_MedicalNotify.controller.Declare.Rest;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.*;
import org.VoPhiHai_MedicalNotify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/declare")
public class DeclareRestController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private HistoryOfExposureService historyOfExposureService;

    @RequestMapping(value = "/create_person/{nationalityId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<HashMap<String,String>> createPerson(@RequestBody Person person, @PathVariable("nationalityId") National nationality){
        person.setNationality(nationality);
        Person personCreated = personService.declare(person);
        HashMap<String,String> idPerson = new HashMap<>();
        idPerson.put("passport", personCreated.getLegalDocument());
        return new ResponseEntity<>(idPerson, HttpStatus.OK);
//        return new ResponseEntity<>(personCreated, HttpStatus.OK);
    }
    @RequestMapping(value = "/create_contact/{personId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<HashMap<String,Long>> create_contact_(
            @RequestBody Contact contact,
            @PathVariable("personId") Person person){
        Contact contactCreated = contactService.change(contact, person);

        HashMap<String, Long> idContact = new HashMap<>();
        idContact.put("idContact", contactCreated.getId());
        return new ResponseEntity<>(idContact, HttpStatus.OK);
    }
    @Autowired
    private TransportService transportService;
    @RequestMapping(value = "/create_transport}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<HashMap<String,Long>> create_transport(
            @RequestBody JsonObject jsonTransport
            ){
        Transport transport = transportService.create(jsonTransport);
        HashMap<String, Long> idTransport = new HashMap<>();
        idTransport.put("idTransport", transport.getId());
        return new ResponseEntity<>(idTransport, HttpStatus.OK);
    }

    @Autowired
    private EntryService entryService;
//    @RequestMapping(value = "/create_entry", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<HashMap<String, String>> create_entry(@RequestBody JsonObject jsonEntry){
//
//    }

}

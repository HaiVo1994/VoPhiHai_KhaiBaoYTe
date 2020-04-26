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
import java.util.List;

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
    public ResponseEntity<JsonObject> createPerson(@RequestBody Person person, @PathVariable("nationalityId") National nationality){
        person.setNationality(nationality);
        Person personCreated = personService.declare(person);
        JsonObject idPerson = new JsonObject();
        idPerson.put("passport", personCreated.getLegalDocument());
        return new ResponseEntity<>(idPerson, HttpStatus.OK);
//        return new ResponseEntity<>(personCreated, HttpStatus.OK);
    }
    @RequestMapping(value = "/create_contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonObject> create_contact(
            @RequestBody JsonObject jsonContact){
        Contact contactCreated = contactService.change(jsonContact);

        JsonObject idContact = new JsonObject();
        idContact.put("idContact", contactCreated.getId());
        return new ResponseEntity<>(idContact, HttpStatus.OK);
    }
    @Autowired
    private TransportService transportService;
    @RequestMapping(value = "/create_transport", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
    @RequestMapping(value = "/create_entry", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonObject> create_entry(@RequestBody JsonObject jsonEntry){
        Entry entry = entryService.create(jsonEntry);
        JsonObject entryId = new JsonObject();
        if (entry!=null){
            entryId.put("entryid", entry.getId());
            entryId.put("messenger", "Cảm Ơn Vì Đã Khai Báo Y Tế");
        }
        else {
            entryId.put("entryid", null);
            entryId.put("messenger", "Hôm Nay Bạn Đã Khai Báo Y Tế");
        }
        return new ResponseEntity<>(entryId,HttpStatus.OK);
    }

    @RequestMapping(value = "/entry_symptom/{entryId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> entry_symptom(@RequestBody List<JsonObject> jsonEntry, @PathVariable String entryId){
        statusService.declare(jsonEntry, entryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/entry_exposure/{entryId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> entry_exposure(@RequestBody List<JsonObject> jsonEntry, @PathVariable String entryId){
        historyOfExposureService.declare(jsonEntry, entryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

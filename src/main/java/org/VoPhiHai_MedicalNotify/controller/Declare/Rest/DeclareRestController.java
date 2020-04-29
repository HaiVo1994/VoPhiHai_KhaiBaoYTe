package org.VoPhiHai_MedicalNotify.controller.Declare.Rest;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.*;
import org.VoPhiHai_MedicalNotify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    @Autowired
    private TransportService transportService;
    @Autowired
    private EntryService entryService;

//    @RequestMapping(value = "/create_person/{nationalityId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<JsonObject> createPerson(@RequestBody Person person, @PathVariable("nationalityId") National nationality){
//        person.setNationality(nationality);
//        Person personCreated = personService.declare(person);
//        JsonObject idPerson = new JsonObject();
//        idPerson.put("passport", personCreated.getLegalDocument());
//        return new ResponseEntity<>(idPerson, HttpStatus.OK);
////        return new ResponseEntity<>(personCreated, HttpStatus.OK);
//    }
//    @RequestMapping(value = "/create_contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<JsonObject> create_contact(
//            @RequestBody JsonObject jsonContact){
//        Contact contactCreated = contactService.change(jsonContact);
//
//        JsonObject idContact = new JsonObject();
//        idContact.put("idContact", contactCreated.getId());
//        return new ResponseEntity<>(idContact, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/create_transport", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<HashMap<String,Long>> create_transport(
//            @RequestBody JsonObject jsonTransport
//            ){
//        Transport transport = transportService.create(jsonTransport);
//        HashMap<String, Long> idTransport = new HashMap<>();
//        idTransport.put("idTransport", transport.getId());
//        return new ResponseEntity<>(idTransport, HttpStatus.OK);
//    }
//

//    @RequestMapping(value = "/create_entry", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<JsonObject> create_entry(@RequestBody JsonObject jsonEntry){
//        Entry entry = entryService.create(jsonEntry);
//        JsonObject entryId = new JsonObject();
//        if (entry!=null){
//            entryId.put("entryid", entry.getId());
//            entryId.put("messenger", "Cảm Ơn Vì Đã Khai Báo Y Tế");
//        }
//        else {
//            entryId.put("entryid", null);
//            entryId.put("messenger", "Hôm Nay Bạn Đã Khai Báo Y Tế");
//        }
//        return new ResponseEntity<>(entryId,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/entry_symptom/{entryId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<Void> entry_symptom(@RequestBody List<JsonObject> jsonEntry, @PathVariable String entryId){
//        statusService.declare(jsonEntry, entryId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/entry_exposure/{entryId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<Void> entry_exposure(@RequestBody List<JsonObject> jsonEntry, @PathVariable String entryId){
//        historyOfExposureService.declare(jsonEntry, entryId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @RequestMapping(value = "/sendDeclare", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonObject> createPerson(@RequestBody JsonObject declare){
        JsonObject result = new JsonObject();
        LinkedHashMap<String,String> personData = (LinkedHashMap<String,String>) declare.get("person");
        LinkedHashMap<String,String> transportData = (LinkedHashMap<String,String>) declare.get("transport");
        LinkedHashMap<String,String> contactData = (LinkedHashMap<String,String>) declare.get("contact");
        LinkedHashMap<String,String> entryData = (LinkedHashMap<String,String>) declare.get("entry");
//        JsonObject personJson = (JsonObject) declare.get("person");
//        JsonObject transportJson = (JsonObject) declare.get("transport");
//        JsonObject contactJson = (JsonObject) declare.get("contact");
//        JsonObject entryJson = (JsonObject) declare.get("entry");
        List<LinkedHashMap<String,String>> statusJsonList = (ArrayList<LinkedHashMap<String,String>>) declare.get("status");
        List<LinkedHashMap<String,String>> historyJsonList = (ArrayList<LinkedHashMap<String,String>>) declare.get("historyOfExposures");
        Person person = personService.declare(personData);
        if (person==null){
            result.put("messenger","Tạo Hồ Sơ Người Dùng Thất Bại");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        Contact contact = contactService.change(contactData, person);
        Transport transport = transportService.create(transportData);
        if (transport == null) {
            result.put("messenger", "Tạo Phương Tiện Nhập Cảnh Thất Bại");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        Entry entry = entryService.create(entryData,person,transport);
        if (entry==null){
           result.put("messenger", "Tạo Tờ Khai Nhập Cảnh Thất Bại");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        List<Status> statusList = statusService.declare(statusJsonList, entry);
        if (statusList==null){
            result.put("messenger", "Tạo Tờ Khai Báo Sức Khỏe Thất Bại");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        List<HistoryOfExposure> historyOfExposureList = historyOfExposureService.declare(historyJsonList, entry);
        if (historyOfExposureList==null){
            result.put("messenger", "Tạo Tờ Khai Báo Tiếp Xúc Thất Bại");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        result.put("messenger", "Khai Báo Thành Công");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

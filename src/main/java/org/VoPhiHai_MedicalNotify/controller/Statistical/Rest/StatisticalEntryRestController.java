package org.VoPhiHai_MedicalNotify.controller.Statistical.Rest;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Entry;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Person;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/statistical_entry")
public class StatisticalEntryRestController {
    @Autowired
    private EntryService entryService;
    @RequestMapping(value = "/amount_people", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Statistical_Entry>> statisticalEntry(@RequestBody JsonObject dateFind){
        List<Statistical_Entry> result = entryService.statisticalEntry(dateFind);
        if (result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/listPeople/{page}/{size}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Page<Statistical_Person>> listEntry(@RequestBody JsonObject dateFind,
                                                              @PathVariable int page,
                                                              @PathVariable int size){
        Page<Statistical_Person> result = entryService.getByImmigrationDate(dateFind, page, size);
        if (result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package org.VoPhiHai_MedicalNotify.controller.Statistical.Rest;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Person;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/statistical_symptom")
public class StatisticalSymptomRestController {
    @Autowired
    private EntryService entryService;
    @Autowired
    private StatusService statusService;
    @RequestMapping(value = "/amount_people", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<JsonObject>> getStatisticalByAmountSymptom(@RequestBody JsonObject timeFind){
        List<JsonObject> statistical = statusService.statisticalByCountSymptom(timeFind);
        if (statistical!=null)
            return new ResponseEntity<>(statistical, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/amount_people/{page}/{size}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonObject> getListPeopleByAmountSymptom(@RequestBody JsonObject data,
                                                                                 @PathVariable int page,
                                                                                 @PathVariable int size){
        JsonObject listPerson = statusService.getListByAmountSymptom(data,size,page);
        if (listPerson!=null)
            return new ResponseEntity<>(listPerson, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/symptomType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Statistical>> getStatisticalBySymptomType(@RequestBody JsonObject timeFind){
        List<Statistical> result = statusService.statisticalByTypeSymptom(timeFind);
        if (result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/symptomType/{page}/{size}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Page<Statistical_Person>> getListPeopleBySymptomType(@RequestBody JsonObject data,
                                                                        @PathVariable int page,
                                                                        @PathVariable int size){
        Page<Statistical_Person> result = statusService.getListBySymptomType(data,size,page);
        if (result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

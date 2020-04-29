package org.VoPhiHai_MedicalNotify.controller.Statistical.Rest;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/statistical")
public class StatisticalSymptomRestController {
    @Autowired
    private EntryService entryService;
    @Autowired
    private StatusService statusService;
    @RequestMapping(value = "/symptom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<JsonObject>> getStatisticalSymptom(@RequestBody JsonObject timeFind){
        List<JsonObject> statistical = statusService.statisticalByCountSymptom(timeFind);
        if (statistical!=null)
            return new ResponseEntity<>(statistical, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

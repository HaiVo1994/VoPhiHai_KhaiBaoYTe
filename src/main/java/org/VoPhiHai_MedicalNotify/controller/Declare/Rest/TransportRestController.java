package org.VoPhiHai_MedicalNotify.controller.Declare.Rest;

import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/transport")
public class TransportRestController {
    @Autowired
    private TransportService transportService;
    @RequestMapping(value = "/getByType/{transportType}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Transport>> getByType(@PathVariable Short transportType){
        List<Transport> transports = transportService.findByType(transportType);
        if (transports.size()>0)
            return new ResponseEntity<>(transports, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

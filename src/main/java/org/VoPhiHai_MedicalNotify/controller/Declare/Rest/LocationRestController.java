package org.VoPhiHai_MedicalNotify.controller.Declare.Rest;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Province;
import org.VoPhiHai_MedicalNotify.model.Ward;
import org.VoPhiHai_MedicalNotify.service.DistrictService;
import org.VoPhiHai_MedicalNotify.service.ProvinceService;
import org.VoPhiHai_MedicalNotify.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/location")
public class LocationRestController {
    @Autowired
    private ProvinceService provinceService;
    @RequestMapping(value = "/province/{nationalId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Province>> getListProvince(@PathVariable Short nationalId){
        return new ResponseEntity<>(provinceService.findAllByNational(nationalId), HttpStatus.OK);
    }

    @Autowired
    private DistrictService districtService;
    @RequestMapping(value = "/district/{provinceId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<District>> getListDistrict(@PathVariable Integer provinceId){
        return new ResponseEntity<>(districtService.findByProvince(provinceId), HttpStatus.OK);
    }

    @Autowired
    private WardService wardService;
    @RequestMapping(value = "/ward/{districtId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Ward>> getListWard(@PathVariable Long districtId){
        return new ResponseEntity<>(wardService.findByDistrict(districtId), HttpStatus.OK);
    }
}

package org.VoPhiHai_MedicalNotify.controller.Declare;

import org.VoPhiHai_MedicalNotify.model.*;
import org.VoPhiHai_MedicalNotify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeclareController {
    @Autowired
    private NationalService nationalService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private ExposureService exposureService;
    @Autowired
    private GateService gateService;
    @Autowired
    private TransportTypeService transportTypeService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView declare(){
        ModelAndView modelAndView = new ModelAndView("declare/declare");
        Iterable<Gate> gates = gateService.findAll();
        Iterable<National> nationals = nationalService.findAll();
        List<Symptom> symptoms = symptomService.findAllEnable();
        List<Exposure> exposures = exposureService.findAllEnable();
        Iterable<TransportType> transportTypes = transportTypeService.getAll();
        modelAndView.addObject("gates", gates);
        modelAndView.addObject("nationals", nationals);
        modelAndView.addObject("symptoms", symptoms);
        modelAndView.addObject("exposures", exposures);
        modelAndView.addObject("transportTypes", transportTypes);
        return modelAndView;
    }
}

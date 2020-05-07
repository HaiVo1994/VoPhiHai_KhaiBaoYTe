package org.VoPhiHai_MedicalNotify.controller.Declare;

import org.VoPhiHai_MedicalNotify.model.*;
import org.VoPhiHai_MedicalNotify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private EntryService entryService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private HistoryOfExposureService historyOfExposureService;
    @RequestMapping(value = "/viewDeclare/{entryId}", method = RequestMethod.GET)
    public ModelAndView viewDeclare(@PathVariable String entryId){
        ModelAndView modelAndView = new ModelAndView("declare/viewDeclare");
        Entry entry = entryService.findById(entryId);
        modelAndView.addObject("entry", entry);

        List<HistoryOfExposure> historyExposure = historyOfExposureService.findByEntry(entry);
        List<Status> statusList = statusService.findByEntry(entry);
        modelAndView.addObject("historyExposure",historyExposure);
        modelAndView.addObject("statusList",statusList);
        Contact contact = contactService.findCurrentContactByPerson(entry.getPerson());
        modelAndView.addObject("contact",contact);
        return modelAndView;
    }
}

package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.*;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Entry;
import org.VoPhiHai_MedicalNotify.repository.EntryRepository;
import org.VoPhiHai_MedicalNotify.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry findById(String id) {
        return entryRepository.findById(id).orElse(null);
    }

    @Override
    public Entry create(Entry entry) {
        Date today = new Date();
        entry.setCreateAt(today);
        String id = entry.getId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");

        id += dateFormat.format(today);
        entry.setId(id);
        if (this.findById(id)!=null)
            return null;
        return entryRepository.save(entry);
    }

    @Override
    public Entry create(Entry entry, String helpDeclareName) {
        entry.setCreateBy(helpDeclareName);
        return this.create(entry);
    }

    @Autowired
    private PersonService personService;
    @Autowired
    private TransportService transportService;
    @Autowired
    private GateService gateService;
    @Autowired
    private ProvinceService provinceService;
    @Override
    public Entry create(LinkedHashMap<String, String> jsonEntry, Person person, Transport transport) {
//        String personId = (String) jsonEntry.get("person");
//        Long transportId = Long.parseLong((String) jsonEntry.get("transport"));
//        Person person = personService.findByPassport(personId);
//        Transport transport = transportService.findById(
//                Long.parseLong((String) jsonEntry.get("transport"))
//        );

        if  ((person!=null) && (transport!=null)){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Entry entry = new Entry();
                entry.setId(person.getPassport() + "_");
                entry.setGate(gateService.findById(Short.parseShort((String) jsonEntry.get("gate"))));
                entry.setTransport(transport);
                entry.setSeatNo((String) jsonEntry.get("seatNo"));
                String date = (String)jsonEntry.get("departureDate");
                entry.setDepartureDate(dateFormat.parse(date));
                date = (String) jsonEntry.get("immigrationDate");
                entry.setImmigrationDate(dateFormat.parse(date));
                entry.setPlaceTravel((String) jsonEntry.get("placeTravel"));
                Province provinceDeparture =
                        provinceService.findById(
                                Integer.parseInt((String)jsonEntry.get("provinceDeparture"))
                        );
                Province provinceDestination =
                        provinceService.findById(Integer.parseInt((String)jsonEntry.get("provinceDestination")));

                entry.setProvinceDeparture(provinceDeparture);
                entry.setProvinceDestination(provinceDestination);
                entry.setPerson(person);
                entry.setListCure((String) jsonEntry.get("listCure"));
                return this.create(entry);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Lỗi Chuyển Đổi Ngày :");
            }
        }
        return null;
    }

    @Override
    public Entry update(Entry entry, String updateName) {
        entry.setUpdateAt(new Date());
        entry.setCreateBy(updateName);
        if (this.findById(entry.getId()) != null){
            return entryRepository.save(entry);
        }
        return null;
    }

    @Override
    public List<Statistical> statisticalSymptomDeparture(Date begin, Date end) {
        List<Statistical> statisticalList = entryRepository.statisticalSymptomDeparture(begin,end);
        if (statisticalList.size()>0)
            return statisticalList;
        return null;
    }

    @Override
    public List<Statistical> statisticalSymptomDeparture(JsonObject timeFind) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = (String) timeFind.get("begin");
            Date begin = dateFormat.parse(date);
            date = (String) timeFind.get("end");
            Date end = dateFormat.parse(date);
            return this.statisticalSymptomDeparture(begin,end);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }

    @Override
    public List<Statistical_Entry> statisticalEntry(Date begin, Date end) {
        List<Statistical_Entry> listResult = entryRepository.statisticalEntry(begin, end);
        if (listResult.size()>0)
            return listResult;
        return null;
    }

    @Override
    public List<Statistical_Entry> statisticalEntry(JsonObject timeFind) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = (String) timeFind.get("begin");
            Date begin = dateFormat.parse(date);
            date = (String) timeFind.get("end");
            Date end = dateFormat.parse(date);
            return this.statisticalEntry(begin,end);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }
}

package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Person;
import org.VoPhiHai_MedicalNotify.repository.StatusRepository;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.VoPhiHai_MedicalNotify.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Status create(Status status, Entry entry, Symptom symptom) {
        status.setEntry(entry);
        status.setSymptom(symptom);
        return this.create(status);
    }

    @Override
    public Status create(Status status) {
        return statusRepository.save(status);
    }

//    @Override
//    public void declare(List<Status> statuses, Entry entry) {
//        Date current = new Date();
//        for(Status status: statuses){
//            status.setDateDeclare(current);
//            status.setEntry(entry);
//            this.create(status);
//        }
//    }

    @Autowired
    private EntryService entryService;
    @Autowired
    private SymptomService symptomService;
    @Override
    public List<Status> declare(List<LinkedHashMap<String,String>> statusJson, Entry entry) {
        Date current = new Date();
        HashMap<String, Symptom> mapSymptom = symptomService.findMapEnable();
//        Entry entry = entryService.findById(entryId);
        if (entry!=null){
            Status status;
            String hasSymptom;
            List<Status> statusList = new ArrayList<>();
            for (LinkedHashMap<String,String> jsonObject: statusJson){
                status = new Status();
                status.setDateDeclare(current);
                status.setEntry(entry);
                status.setSymptom(mapSymptom.get((String.valueOf(jsonObject.get("symptom")))));
                hasSymptom = String.valueOf(jsonObject.get("haveSymptom"));
                if (hasSymptom.equals("1")){
                    status.setHaveSymptom(true);
                }
                else
                    status.setHaveSymptom(false);
//                status.setHaveSymptom((boolean));
                status = this.create(status);
                statusList.add(status);
            }
            return statusList;
        }
        return null;
    }

    @Override
    public List<Entry> getListEntryHaveSymptom(Date begin, Date end) {
        return null;
    }

    @Override
    public List<Entry> getListEntryHaveSymptomById(Date begin, Date end, Long symptomId) {
        return null;
    }

    @Override
    public List<JsonObject> statisticalByCountSymptom(Date begin, Date end) {
        List<Statistical> statisticalList = statusRepository.countSymptom(begin,end);
        HashMap<Long,Long> symptomCount = new HashMap<>();
        long newValue;
        for(Statistical statistical_entry : statisticalList){
            symptomCount.computeIfAbsent(statistical_entry.getCount(), k -> (long) 0);
            newValue = symptomCount.get(statistical_entry.getCount()) + 1;
            symptomCount.put(statistical_entry.getCount(), newValue);
        }

        List<JsonObject> result = new ArrayList<>();
        symptomCount.forEach((keyCount, count)->{
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("numberSymptom", keyCount);
            jsonObject.put("amountEntry", count);
            result.add(jsonObject);
        });
        if (result.size()>0)
            return result;
//        if (statistical.size()>0)
//            return statistical;
        return null;
    }

    @Override
    public List<JsonObject> statisticalByCountSymptom(JsonObject dateEntry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = (String) dateEntry.get("begin");
            Date begin = dateFormat.parse(date);
            date = (String) dateEntry.get("end");
            Date end = dateFormat.parse(date);
            return this.statisticalByCountSymptom(begin,end);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }

    @Override
    public List<Statistical> statisticalByTypeSymptom(Date begin, Date end) {
        List<Statistical> result = statusRepository.countForSymptomType(begin,end);
        if (result.size()>0)
            return result;
        return null;
    }

    @Override
    public List<Statistical> statisticalByTypeSymptom(JsonObject dateEntry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = (String) dateEntry.get("begin");
            Date begin = dateFormat.parse(date);
            date = (String) dateEntry.get("end");
            Date end = dateFormat.parse(date);
            return this.statisticalByTypeSymptom(begin,end);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }

    @Override
    public JsonObject getListByAmountSymptom(Date begin, Date end, long amount, int size, int page) {
        if (page<0 || size<=0){
            return null;
        }
        List<Statistical_Person> listPeople =
                statusRepository.getListPersonByAmountSymptom(begin, end);
        if (listPeople.size()>0){
            List<Statistical_Person> listResult = new ArrayList<>();
            for(Statistical_Person person: listPeople){
                if (person.getValue()==amount)
                    listResult.add(person);
            }
            int pageAmount = (int) Math.ceil((double)listResult.size()/size);
            if (page + 1 > pageAmount)
                return null;
            int beginLocation = page * size;
            int endLocation = beginLocation + size;
            if (endLocation>listResult.size())
                endLocation = listResult.size();
            JsonObject jsonResult = new JsonObject();
            jsonResult.put("listPeople", listResult.subList(beginLocation,endLocation));
            jsonResult.put("pageAmount", pageAmount);
            jsonResult.put("page", page);
            jsonResult.put("size", size);
            return jsonResult;
        }
        return null;
    }

    @Override
    public JsonObject getListByAmountSymptom(JsonObject data, int size, int page) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return this.getListByAmountSymptom(
                    dateFormat.parse((String) data.get("begin")),
                    dateFormat.parse((String) data.get("end")),
                    Long.parseLong((String) data.get("amount")),
                    size,
                    page
            );
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }

    @Override
    public Page<Statistical_Person> getListBySymptomType(Date begin, Date end, Symptom symptom, Pageable pageable) {
        Page<Statistical_Person> listPeople = statusRepository.getListPersonBySymptomType(begin,end,symptom,pageable);
        if (listPeople.getSize()>0)
            return listPeople;
        return null;
    }

    @Override
    public Page<Statistical_Person> getListBySymptomType(JsonObject data, int size, int page) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Symptom symptom = symptomService.findByName((String) data.get("symptom"));
            return this.getListBySymptomType(
                    dateFormat.parse((String) data.get("begin")),
                    dateFormat.parse((String) data.get("end")),
                    symptom,
                    PageRequest.of(page,size)
            );
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi Chuyển Đổi Ngày :");
        }
        return null;
    }
}

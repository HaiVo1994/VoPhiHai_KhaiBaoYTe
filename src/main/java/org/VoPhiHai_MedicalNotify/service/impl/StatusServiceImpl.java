package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.repository.StatusRepository;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.VoPhiHai_MedicalNotify.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;

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
}

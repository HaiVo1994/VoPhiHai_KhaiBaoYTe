package org.VoPhiHai_MedicalNotify.model.support;

public class Statistical_Entry {
    private String entryId;
    private Integer value;

    public Statistical_Entry(String entryId, Integer value) {
        this.entryId = entryId;
        this.value = value;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

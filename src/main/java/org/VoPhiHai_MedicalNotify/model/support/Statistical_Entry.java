package org.VoPhiHai_MedicalNotify.model.support;

import java.util.Date;

public class Statistical_Entry {
    private Date entryDate;
    private Long value;

    public Statistical_Entry(Date entryDate, Long value) {
        this.entryDate = entryDate;
        this.value = value;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}

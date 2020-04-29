package org.VoPhiHai_MedicalNotify.model.support;

public class Statistical {
    private String tittle;
    private Long count;

    public Statistical(String tittle, Long count) {
        this.tittle = tittle;
        this.count = count;
    }

    public Statistical(Integer tittle, Long count){
        this.tittle = String.valueOf(tittle);
        this.count = count;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

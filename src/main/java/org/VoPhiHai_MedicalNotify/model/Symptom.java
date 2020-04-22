package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "symptom")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Date disable_at;
    @Column(length = 200)
    private String disable_by;
    @ColumnDefault(value = "1")
    private boolean isEnabled;
    private Date create_at;
    @Column(length = 200)
    private String create_by;
    private Date update_at;
    @Column(length = 200)
    private String update_by;

    @OneToMany(mappedBy = "symptom", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Status> statuses;

    public Symptom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDisable_at() {
        return disable_at;
    }

    public void setDisable_at(Date disable_at) {
        this.disable_at = disable_at;
    }

    public String getDisable_by() {
        return disable_by;
    }

    public void setDisable_by(String disable_by) {
        this.disable_by = disable_by;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}

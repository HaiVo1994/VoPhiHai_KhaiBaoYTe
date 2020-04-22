package org.VoPhiHai_MedicalNotify.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 200)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    private District district;
    private String address;
    @Column(length = 20)
    private String phone;
    private String email;

    @OneToMany(mappedBy = "contactHelpDeclare", fetch = FetchType.LAZY)
    private List<Entry> entries;

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

    public Contact() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
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
}

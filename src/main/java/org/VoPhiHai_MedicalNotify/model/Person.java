package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(length = 50)
    private String legalDocument;
    @Column(length = 200)
    private String name;
    private short birthYear;
    private byte gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nationalityId")
    private National Nationality;
    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Contact> contacts;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> entries;

    @Column(length = 200)
    private String createBy;
    private Date createAt;
    private boolean isDelete;
    @Column(length = 200)
    private String updateBy;
    private Date updateAt;

    public Person() {
    }

    public String getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(short birthYear) {
        this.birthYear = birthYear;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public National getNationality() {
        return Nationality;
    }

    public void setNationality(National nationality) {
        Nationality = nationality;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}

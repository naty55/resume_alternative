package com.ra.resume_alternative.resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "resume_details")
public class ResumeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long detailId;
    private String value;
    private DetailType detailType;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    public ResumeDetail() {

    }
    public ResumeDetail(String value, DetailType detailType) {
        this.value = value;
        this.detailType = detailType;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value.toLowerCase();
    }
    public DetailType getDetailType() {
        return detailType;
    }
    public void setDetailType(DetailType detailType) {
        this.detailType = detailType;
    }
    public Resume getResume() {
        return resume;
    }
    public void setResume(Resume resume) {
        this.resume = resume;
    }
    

}

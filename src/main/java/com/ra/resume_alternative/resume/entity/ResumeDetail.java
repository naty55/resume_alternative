package com.ra.resume_alternative.resume.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="details")
@Table(uniqueConstraints = {@UniqueConstraint(name = "DUC", columnNames = {"value", "detailType", "userId"})})
public class ResumeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long detailId;
    private String value;
    private DetailType detailType;

    @Column(nullable = false)
    @JsonIgnore
    private Long userId;

    @ManyToMany(mappedBy = "details")
    private Set<Resume> resumes;

    public ResumeDetail() {

    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
}

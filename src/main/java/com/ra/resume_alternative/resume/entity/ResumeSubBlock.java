package com.ra.resume_alternative.resume.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "subblocks")
public class ResumeSubBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subblockId;
    private String subblockTitle;
    private String content;
    private Date startTime;
    private Date endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id")
    @JsonIgnore
    private ResumeBlock block;


    public Long getSubblockId() {
        return subblockId;
    }
    public Long getBlockId() {
        return block.getBlockId();
    }
    public void setBlock(ResumeBlock block) {
        this.block = block;
    }
    public void setSubblockId(Long subblockId) {
        this.subblockId = subblockId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSubblockTitle() {
        return subblockTitle;
    }
    public void setSubblockTitle(String subblockName) {
        this.subblockTitle = subblockName;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    

}

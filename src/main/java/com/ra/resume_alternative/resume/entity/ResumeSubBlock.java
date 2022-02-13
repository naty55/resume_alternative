package com.ra.resume_alternative.resume.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "subblocks")
public class ResumeSubBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subblockId;
    private String subblockTitle;
    private String content;
    private Date startTime;
    private Date endTime;

    @Column(nullable = false)
    private Long blockId;


    public Long getSubblockId() {
        return subblockId;
    }
    public Long getBlockId() {
        return blockId;
    }
    public void setBlockId(Long blockId) {
        this.blockId = blockId;
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

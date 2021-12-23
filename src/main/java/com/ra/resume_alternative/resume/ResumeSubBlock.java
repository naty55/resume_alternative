package com.ra.resume_alternative.resume;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ResumeSubBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subblockId;
    private String subblockTitle;
    private String content;
    private Date from;
    private Date to;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private ResumeBlock block;

    public Long getSubblockId() {
        return subblockId;
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
    public ResumeBlock getBlock() {
        return block;
    }
    public void setBlock(ResumeBlock block) {
        this.block = block;
    }
    public String getSubblockTitle() {
        return subblockTitle;
    }
    public void setSubblockTitle(String subblockName) {
        this.subblockTitle = subblockName;
    }
    public Date getFrom() {
        return from;
    }
    public void setFrom(Date from) {
        this.from = from;
    }
    public Date getTo() {
        return to;
    }
    public void setTo(Date to) {
        this.to = to;
    }
    

    

}

package com.ra.resume_alternative.resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "blocks")
public class ResumeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long blockId;
    Integer BlockOrder;

    String paragarphs= "Block test";

    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    Resume resume;


    public Long getBlockId() {
        return blockId;
    }


    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }


    public Integer getBlockOrder() {
        return BlockOrder;
    }


    public void setBlockOrder(Integer blockOrder) {
        BlockOrder = blockOrder;
    }


    public String getParagarphs() {
        return paragarphs;
    }


    public void setParagarphs(String paragarphs) {
        this.paragarphs = paragarphs;
    }


    public Resume getResume() {
        return resume;
    }


    public void setResume(Resume resume) {
        this.resume = resume;
    }

    
    
}

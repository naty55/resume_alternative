package com.ra.resume_alternative.resume.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name="blocks")
public class ResumeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blockId;
    @Check(constraints = "blockOrder > 0")
    private Integer blockOrder;
    private String blockName;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "block")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<ResumeSubBlock> subblocks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    public ResumeBlock() {}


    public Long getBlockId() {
        return blockId;
    }


    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }


    public Integer getBlockOrder() {
        return blockOrder;
    }


    public void setBlockOrder(Integer blockOrder) {
        this.blockOrder = blockOrder;
    }



    public void addSubBlock(ResumeSubBlock subBlock) {
        subblocks.add(subBlock);
        subBlock.setBlock(this);
    }
    public void removeSubBlock(ResumeSubBlock subBlock) {
        if (subblocks.contains(subBlock)) {
            subblocks.remove(subBlock);
            subBlock.setBlock(null);
        }
    }


    public String getBlockName() {
        return blockName;
    }


    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }


    public Set<ResumeSubBlock> getSubblocks() {
        return new HashSet<>(subblocks);
    }


    public void setSubblocks(Set<ResumeSubBlock> subblocks) {
        subblocks.forEach(s -> addSubBlock(s));
    }


    public Long getResumeId() {
        if(resume != null){
            return resume.getResumeId();
        }
        return null;
    }

    public Resume getResume() {
        return resume;
    }


    public void setResume(Resume resume) {
        if(this.resume != null) {
            resume.removeBlock(this);
        }
        this.resume = resume;
    }


    @Override
    public String toString() {
        return "ResumeBlock [blockId=" + blockId + ", blockName=" + blockName + ", blockOrder=" + blockOrder
                + ", resumeId=" + getResumeId() + ", subblocks=" + subblocks + "]";
    }

    

    
    
    
}

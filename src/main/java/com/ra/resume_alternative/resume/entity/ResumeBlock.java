package com.ra.resume_alternative.resume.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name="blocks")
public class ResumeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blockId;
    private Integer blockOrder;
    private String blockName;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy ="blockId")
    Set<ResumeSubBlock> subblocks = new HashSet<>();

    @Column(nullable = false)
    private Long resumeId;

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
        subBlock.setBlockId(blockId);
    }
    public void removeSubBlock(ResumeSubBlock subBlock) {
        if (subblocks.contains(subBlock)) {
            subblocks.remove(subBlock);
            subBlock.setBlockId(null);
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
        return resumeId;
    }


    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    

    
    
    
}

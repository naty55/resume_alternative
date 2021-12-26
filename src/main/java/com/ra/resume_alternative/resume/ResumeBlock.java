package com.ra.resume_alternative.resume;

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

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name = "blocks")
public class ResumeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blockId;
    private Integer blockOrder;
    private String blockName;
    
    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    Resume resume;

    @OneToMany(mappedBy = "block", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<ResumeSubBlock> subblocks = new HashSet<>();

    public ResumeBlock() {
        blockOrder = 1;
        blockName = "Profile";
        addSubBlock(new ResumeSubBlock());
    }


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

    public Resume getResume() {
        return resume;
    }


    public void setResume(Resume resume) {
        this.resume = resume;
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

    
    
    
}

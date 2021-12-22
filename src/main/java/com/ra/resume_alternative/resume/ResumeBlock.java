package com.ra.resume_alternative.resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "blocks")
public class ResumeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long blockId;
    Integer BlockOrder;

    
    @ManyToOne
    Resume resume;
    
}

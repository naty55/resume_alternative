package com.ra.resume_alternative.resume;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ra.resume_alternative.user.User;



@Entity(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long resumeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    String title;

    @OneToMany(mappedBy = "resume", orphanRemoval = true)
    Set<ResumeBlock> blocks;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "resume_id", nullable=false)
    Set<ResumeSkill> skills;
    
}
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
import com.ra.resume_alternative.user.User;


@Entity(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String title;
    private String styleName;
    

    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ResumeBlock> blocks = new HashSet<>();

    @OneToMany(mappedBy ="resume", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ResumeSkill> skills = new HashSet<>();

    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ResumeDetail> details = new HashSet<>();

    public Resume() {

    }

    public Resume(User user,String title, Set<ResumeBlock> blocks, Set<ResumeSkill> skills) {
        this.user = user;
        this.title = title;
        setBlocks(blocks);
        setSkills(skills);
    }
    public Long getResumeId() {
        return resumeId;
    }
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Set<ResumeBlock> getBlocks() {
        return blocks;
    }
    public void addBlock(ResumeBlock block) {
        blocks.add(block);
        block.setResume(this);
    }
    public void removeBlock(ResumeBlock block) {
        if (blocks.contains(block)) {
            blocks.remove(block);
            block.setResume(null);
        }
    }
    public void setBlocks(Set<ResumeBlock> blocks) {
        blocks.forEach(b -> this.addBlock(b));
    }
    public Set<ResumeSkill> getSkills() {
        return skills;
    }
    public void setSkills(Set<ResumeSkill> skills) {
        skills.forEach(s -> this.addSkill(s));
    }
    public void addSkill(ResumeSkill skill) {
        skills.add(skill);
        skill.setResume(this);
    }
    public void removeSkill(ResumeSkill skill) {
        if(skills.contains(skill)) {
            skills.remove(skill);
            skill.setResume(null);
        }
    }

    public String getStyleName() {
        return styleName;
    }
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    

    
}
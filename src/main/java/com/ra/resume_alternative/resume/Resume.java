package com.ra.resume_alternative.resume;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
    @JoinColumn(name = "user_id")
    User user;

    String title;

    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<ResumeBlock> blocks = new HashSet<>();

    @OneToMany(mappedBy ="resume", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<ResumeSkill> skills = new HashSet<>();

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
        blocks.remove(block);
        block.setResume(null);
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
        skills.remove(skill);
        skill.setResume(null);
    }

    

    
}
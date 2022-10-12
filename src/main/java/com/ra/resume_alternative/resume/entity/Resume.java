package com.ra.resume_alternative.resume.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.resume_alternative.user.User;


@Entity(name="resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resumeId;

    @JoinColumn(name = "user_id", nullable = false)
    @Column(nullable = false)
    private Long userId;
    private String title;
    @ColumnDefault("default")
    private String styleName;
    
    @JsonIgnore
    private LocalDateTime lastTimeAccessed;
    

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "resume")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("blockOrder")
    private Set<ResumeBlock> blocks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "resumes_skills", 
        joinColumns = {@JoinColumn(name="resume_id")},
        inverseJoinColumns = {@JoinColumn(name="skill_id")} 
        )
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Set<ResumeSkill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "resumes_details", 
        joinColumns = {@JoinColumn(name="resume_id")},
        inverseJoinColumns = {@JoinColumn(name="detail_id")}
        )
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Set<ResumeDetail> details = new HashSet<>();

    public Resume() {

    }

    public Resume(User user,String title, Set<ResumeBlock> blocks, Set<ResumeSkill> skills) {
        this.userId = user.getUserId();
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
        this.skills.clear();
        skills.forEach(s -> this.addSkill(s));
    }
    public void addSkill(ResumeSkill skill) {
        skills.add(skill);
        skill.setUserId(this.userId);
    }
    public void removeSkill(ResumeSkill skill) {
        if(skills.contains(skill)) {
            skills.remove(skill);
        }
    }

    public void setDetails(Set<ResumeDetail> details) {
        this.details.clear();
        details.forEach(d -> addDetail(d));
    }
    public void addDetail(ResumeDetail d) {
        details.add(d);
        d.setUserId(this.userId);
    }

    public String getStyleName() {
        return styleName;
    }
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Set<ResumeDetail> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Resume [blocks=" + blocks + ", details=" + details + ", resumeId=" + resumeId + ", skills=" + skills
                + ", styleName=" + styleName + ", title=" + title + ", user=" + userId + "]";
    }    
}
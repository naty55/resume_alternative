package com.ra.resume_alternative.resume.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(uniqueConstraints = {@UniqueConstraint(name="TNU", columnNames = {"skillName", "skillType", "userId"})})
@Entity(name="skills")
public class ResumeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long skillId;
    private String skillName;
    private SkillLevel skillLevel;
    private SkillType skillType;

    @Column(nullable = false)
    @JsonIgnore
    private Long userId;

    public ResumeSkill() {

    }
    

    public ResumeSkill(String skillName, SkillLevel skillLevel, SkillType skillType) {
        setSkillName(skillName);
        setSkillLevel(skillLevel);
        setSkillType(skillType);
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName.toLowerCase();
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }
    public SkillType getSkillType() {
        return skillType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "ResumeSkill [skillId=" + skillId + ", skillLevel=" + skillLevel + ", skillName="
                + skillName + ", skillType=" + skillType + "]";
    }

    

    

}




package com.ra.resume_alternative.resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "skills")
public class ResumeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long skillId;
    private String skillName;
    private Integer skillLevel;
    private SkillType skillType;
    

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    Resume resume;

    public ResumeSkill() {

    }
    
    public ResumeSkill(String skillName, Integer level, SkillType skillType) {
        setSkillName(skillName);
        setSkillId(skillId); 
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

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public String toString() {
        return "ResumeSkill [resume=" + resume + ", skillId=" + skillId + ", skillLevel=" + skillLevel + ", skillName="
                + skillName + ", skillType=" + skillType + "]";
    }

    

    

}

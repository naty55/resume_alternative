package com.ra.resume_alternative.resume;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "skills")
public class ResumeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long skillId;
    String skillName;
    Integer level;

}

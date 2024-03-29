package com.ra.resume_alternative.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.entity.ResumeDetail;
import com.ra.resume_alternative.resume.entity.ResumeSkill;




@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Date created;
    private String roles;
    private boolean verfiedEmail;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    Set<ResumeSkill> skills = new HashSet<>();
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    Set<ResumeDetail> details = new HashSet<>();
    
    @OneToMany(mappedBy = "userId", orphanRemoval = true, fetch = FetchType.LAZY)
    @OnDelete(action=OnDeleteAction.CASCADE)
    Set<Resume> resumes = new HashSet<>();


    public User(String name, String email, String password, String date, String roles, boolean verfiedEmail) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.username = name;
        this.email = email;
        this.password = password;
        this.created = format.parse(date);
        this.roles = roles;
        this.verfiedEmail = verfiedEmail;
    }

    public User() {}

    public User(String name, String email, String password, String date, String roles) throws ParseException {
        this(name, email, password, date, roles, true);
    }

    public boolean isVerfiedEmail() {
        return verfiedEmail;
    }
    public void setVerfiedEmail(boolean verfiedEmail) {
        this.verfiedEmail = verfiedEmail;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long id) {
        this.userId = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setResumes(Set<Resume> resumes) {
        resumes.forEach(r -> this.addResume(r));
    }
    public Set<Resume> getResumes() {
        return this.resumes;
    }
    public void addResume(Resume resume) {
        this.resumes.add(resume);
        // resume.setUser(this);
    }
    public void removeResume(Resume resume) {
        if(resumes.contains(resume)) {
            resumes.remove(resume);
            // resume.setUser(null);
        }
    }

    public void addDetail(ResumeDetail detail) {
        this.details.add(detail);
        detail.setUserId(this.userId);
    }

    
    public void addSkill(ResumeSkill skill) {
        skills.add(skill);
        skill.setUserId(this.userId);
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + userId + ", name=" + username + ", password=" + password + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    

  
}

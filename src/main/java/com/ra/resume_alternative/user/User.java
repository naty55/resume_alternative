package com.ra.resume_alternative.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Date created;
    private String roles;
    private boolean verfiedEmail;

    @Transient
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    
    public User(long id, String name, String email, String password, String date, String roles, boolean verfiedEmail) throws ParseException {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = format.parse(date);
        this.roles = roles;
        this.verfiedEmail = verfiedEmail;
    }

    public User(String name, String email, String password, String date, String roles) throws ParseException {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = format.parse(date);
        this.roles = roles;
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
    public User() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", name=" + name + ", password=" + password + "]";
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
package com.jobportal.JobPortal.Recruiter.Recruiters;

import com.jobportal.JobPortal.Recruiter.Job.Job;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recruiter")
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterId;
    private String name;
    private String address;
    @Column(unique = true, nullable = false)
    private String email;
    private Double phoneNumber;
    private String company;
    private String password;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Job> job = new HashSet<>();
    public String getCompany() {
        return company;
    }
    public Set<Job> getJob() {
        return job;
    }

    public void setJob(Set<Job> job) {
        this.job = job;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public void setPhoneNumber(Double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Double getPhoneNumber() {
        return phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    @Override
//    public String toString() {
//        return "Recruiter{" +
//                "recruiterId=" + recruiterId +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", email='" + email + '\'' +
//                ", company='" + company + '\'' +
//                ", job=" + job +
//                '}';
//    }

}


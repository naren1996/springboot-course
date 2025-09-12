package com.jobportal.JobPortal.Recruiter.Recruiters;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterLoginRequest;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;
    //private final RecruiterRepository recruiterRepository;
    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;

    }

    //signup
    public void signup(RecruiterSignupRequest request) {
        Recruiter recruiter = new Recruiter();
        recruiter.setName(request.getName());
        recruiter.setAddress(request.getAddress());
        recruiter.setEmail(request.getEmail());
        recruiter.setCompany(request.getCompany());
        recruiter.setPhoneNumber(Double.valueOf(request.getPhoneNumber()));
        recruiter.setPassword(request.getPassword()); // hash password

        recruiterRepository.save(recruiter);
    }
    public boolean emailExists(String email) {
        return recruiterRepository.findByEmail(email).isPresent();
    }

   //create User
    public String createUser(Recruiter recruiter){
    recruiterRepository.save(recruiter);
    return "Requiter added successfully!";
   }

   //login
    public boolean login(RecruiterLoginRequest request){
        Optional<Recruiter> recruiterOpt = recruiterRepository.findByEmail(request.getEmail());

        if(recruiterOpt.isEmpty()) {
            return false;
        }
        Recruiter recruiter = recruiterOpt.get();

        return request.getPassword().equals(recruiter.getPassword());
    }
}

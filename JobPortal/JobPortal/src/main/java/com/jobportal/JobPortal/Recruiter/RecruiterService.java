package com.jobportal.JobPortal.Recruiter;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;
    //private final RecruiterRepository recruiterRepository;
    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
       // this.passwordEncoder = passwordEncoder;
    }
    //signup
//

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

//create User
public String createUser(Recruiter recruiter){
    recruiterRepository.save(recruiter);
    return "Requiter added successfully!";
   }
}

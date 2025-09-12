package com.jobportal.JobPortal.Recruiter.Recruiters;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterLoginRequest;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

//    @PostMapping("/signup")
//    public String signup(@RequestBody Recruiter user){
//        return recruiterService.signup(user);
//    }

    @PostMapping("/signup")
    public String signup(@RequestBody RecruiterSignupRequest request){
        //check if email already exists
        if (recruiterService.emailExists(request.getEmail())) {
            return "This email is already registered. Please use a different email.";
        }
        recruiterService.signup(request);
        return "Registered Successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody RecruiterLoginRequest request) {
        boolean success = recruiterService.login(request);
        return success ? "Login Successful" : "Invalid email or password";
    }
}

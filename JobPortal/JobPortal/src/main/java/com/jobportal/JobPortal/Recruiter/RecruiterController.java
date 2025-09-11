package com.jobportal.JobPortal.Recruiter;
import com.jobportal.JobPortal.Recruiter.dto.RecruiterSignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> signup( @RequestBody RecruiterSignupRequest request) {
        recruiterService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Recruiter registered successfully");
    }
}

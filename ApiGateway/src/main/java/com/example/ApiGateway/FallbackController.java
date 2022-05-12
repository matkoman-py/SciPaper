package com.example.ApiGateway;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallbackMethod(){
        return "User service is taking longer than expected!";
    }
    @GetMapping("/sciPaperServiceFallback")
    public String sciPaperServiceFallbackMethod(){
        return "Scipaper service is taking longer than expected!";
    }
    @GetMapping("/libraryServiceFallback")
    public String libraryServiceFallbackMethod(){
        return "Library service is taking longer than expected!";
    }
}

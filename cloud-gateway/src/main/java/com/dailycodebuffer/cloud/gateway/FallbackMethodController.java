package com.dailycodebuffer.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/userServiceFallback")
    public String UserServiceFallbackMethod() {
        return "User service is taking longer than expected." +
                "Please try again later.";
    }

    @GetMapping("/departmentServiceFallback")
    public String departmentServiceFallbackMethod() {
        return "Department service is taking longer than expected." +
                "Please try again later.";
    }
}

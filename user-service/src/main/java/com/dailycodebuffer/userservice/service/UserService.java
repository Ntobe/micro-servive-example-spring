package com.dailycodebuffer.userservice.service;

import com.dailycodebuffer.userservice.VO.Department;
import com.dailycodebuffer.userservice.VO.ResponseTemplateVO;
import com.dailycodebuffer.userservice.entity.User;
import com.dailycodebuffer.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;

    private final ReactiveCircuitBreaker readingListCircuitBreaker;

    public UserService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = WebClient.builder().build();
        this.readingListCircuitBreaker = circuitBreakerFactory.create("recommended");
    }

    public User saveUser(User user) {

        log.info("Inside saveUser method of UserService.");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {

        log.info("Inside saveUser method of UserService.");
        User user = userRepository.findByUserId(userId);

        ResponseTemplateVO vo = new ResponseTemplateVO();

        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class);

        /*readingListCircuitBreaker.run(webClient.get()
                .uri("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId())
                .retrieve().bodyToMono(Department.class)).subscribe(result -> {
            vo.setUser(user);
            vo.setDepartment(result);
        });*/

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }
}

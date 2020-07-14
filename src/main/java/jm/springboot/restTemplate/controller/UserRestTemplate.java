package jm.springboot.restTemplate.controller;

import jm.springboot.restTemplate.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restTemplate")
public class UserRestTemplate {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @GetMapping(value = "/code")
    public ResponseEntity<String> getUser() {
        return new ResponseEntity<>(restTemplateConfig.getFinalCode(), HttpStatus.OK);
    }
}

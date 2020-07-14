package jm.springboot.restTemplate.config;

import jm.springboot.restTemplate.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private String url = "http://91.241.64.178:7081/api/users";
    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private UserDto getUser(int id) {
        UserDto userDto = new UserDto(3L, "James", "Brown", (byte) 28);
        if (id == 1) {
            return userDto;
        } else if (id == 2) {
            userDto.setName("Thomas");
            userDto.setLastName("Shelby");
            return userDto;
        } else
            return new UserDto();
    }

    public String getPostCode(HttpHeaders headers) {
        HttpEntity<UserDto> entity = new HttpEntity<>(getUser(1), headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }

    public String getPutCode(HttpHeaders headers) {
        HttpEntity<UserDto> entity = new HttpEntity<>(getUser(2), headers);
        return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class).getBody();
    }

    public String getDeleteCode(HttpHeaders headers) {
        HttpEntity<UserDto> entity = new HttpEntity<>(getUser(2), headers);
        return restTemplate.exchange(url+"/"+entity.getBody().getId(), HttpMethod.DELETE, entity, String.class).getBody();
    }

    public String getFinalCode() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", response.getHeaders().get("Set-Cookie").get(0));

        return getPostCode(headers) + getPutCode(headers) + getDeleteCode(headers);
    }
}

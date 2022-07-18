package com.example.springbootrandomusergenerator.service;

import com.example.springbootrandomusergenerator.model.User;
import com.example.springbootrandomusergenerator.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGenerateServiceImpl implements UserGenerateService{

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Override
    public User userGenerate() {


        JSONObject root = new JSONObject(callAPI());

        JSONObject main = root.getJSONArray("results").getJSONObject(0);

        String id=main.getJSONObject("login").get("uuid").toString();

        String firstName = main.getJSONObject("name").get("first").toString();

        String phone = main.get("phone").toString();

        String country=main.getJSONObject("location").get("country").toString();

        String email=main.get("email").toString();

        String userName=main.getJSONObject("login").get("username").toString();

        User user= User.builder()
                .id(id)
                .firstName(firstName)
                .phone(phone)
                .country(country)
                .email(email)
                .userName(userName)
                .build();

        userRepository.save(user);

        return user;
    }

    @Override
    public String generatorValue() {
        JSONObject root = new JSONObject(callAPI());

        JSONObject main = root.getJSONArray("results").getJSONObject(0);

        return main.toString();
    }


    private String callAPI(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>(headers);

        return restTemplate.exchange("https://randomuser.me/api/", HttpMethod.GET,entity,String.class).getBody();
    }
}

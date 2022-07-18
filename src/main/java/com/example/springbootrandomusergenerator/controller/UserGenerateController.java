package com.example.springbootrandomusergenerator.controller;

import com.example.springbootrandomusergenerator.model.User;
import com.example.springbootrandomusergenerator.service.UserGenerateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/random/generator/user")
public class UserGenerateController {

    final UserGenerateService userGenerateService;


    @PostMapping("/generate")
    public User userGenerator(){
      return  userGenerateService.userGenerate();
    }

    @PostMapping("/value")
    public String generatorValue(){
        return userGenerateService.generatorValue();
    }
}

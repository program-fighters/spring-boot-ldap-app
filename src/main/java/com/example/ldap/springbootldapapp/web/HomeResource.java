package com.example.ldap.springbootldapapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

  @GetMapping("/")
  @ResponseBody
  public String index() {
    return "Welcome to the home page!";
  }
}

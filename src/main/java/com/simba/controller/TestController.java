package com.simba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

  @RequestMapping("/test")
  public String test(){
    return "/talk/test";
  }

  @RequestMapping("/home")
  public String home(){
    return "/talk/home";
  }

}

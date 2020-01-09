package com.simba.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@Log4j
public class HomeController {

  @RequestMapping("/start")
  public String start(ModelMap modelMap) {
    log.info("start");
    modelMap.put("list","");
    return "/talk/home";
  }
}

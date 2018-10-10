package net.moonj.admgc.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.kaptcha.Kaptcha;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

  @Autowired
  private Kaptcha kaptcha;

  @GetMapping("/render")
  public void render() {
    kaptcha.render();
  }

  @PostMapping("/valid")
  public void validDefaultTime(@RequestParam String code) {
    //default timeout 900 seconds
    kaptcha.validate(code);
  }

  @PostMapping("/validTime")
  public void validWithTime(@RequestParam String code) {
    kaptcha.validate(code, 60);
  }

}
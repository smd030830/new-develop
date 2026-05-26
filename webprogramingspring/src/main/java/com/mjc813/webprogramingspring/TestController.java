package com.mjc813.webprogramingspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "hello"; // hello.jsp 파일을 찾음
    }
}
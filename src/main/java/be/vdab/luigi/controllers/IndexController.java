package be.vdab.luigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index(){
        return "index";
    }
}

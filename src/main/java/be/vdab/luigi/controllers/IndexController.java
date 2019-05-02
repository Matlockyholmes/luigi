package be.vdab.luigi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index(){
        StringBuffer buffer = new StringBuffer("<!doctype html><html><title>Hallo</title><body>");
        int uur = LocalTime.now().getHour();
        if(uur < 12){
            buffer.append("Goede morgen");
        } else if (uur < 18){
            buffer.append("Goede middag");
        } else {
            buffer.append("Goede avond");
        }
        buffer.append("</body><html>");
        return buffer.toString();
    }
}

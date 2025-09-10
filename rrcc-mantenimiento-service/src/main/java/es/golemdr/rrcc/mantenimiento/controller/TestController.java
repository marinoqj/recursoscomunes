package es.golemdr.rrcc.mantenimiento.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${test.value}")
    private String value;

    @GetMapping("/test")
    public String getValue() {
        return value;
    }
}
 

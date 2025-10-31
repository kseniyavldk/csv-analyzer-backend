package com.example.csvanalyzer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping({"/upload", "/history"})
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}

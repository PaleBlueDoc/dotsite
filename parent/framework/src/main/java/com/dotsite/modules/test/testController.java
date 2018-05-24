package com.dotsite.modules.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = {""})
    public String list(Model model) {
        return "test";
    }

}

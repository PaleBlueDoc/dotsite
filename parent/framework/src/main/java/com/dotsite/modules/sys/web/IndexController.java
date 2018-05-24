package com.dotsite.modules.sys.web;

import com.dotsite.modules.conf.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

    @RequestMapping(value = {"", "index"})
    public String list(Model model) {
        return "index";
    }

}

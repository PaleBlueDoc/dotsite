package com.dotsite.modules.sys.web;

import com.dotsite.common.lang.StringUtils;
import com.dotsite.modules.conf.controller.BaseController;
import com.dotsite.modules.sys.entity.SysUser;
import com.dotsite.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @ModelAttribute
    public SysUser get(@RequestParam(required = false) String userCode) {
        if (StringUtils.isNotEmpty(userCode)) {
            Optional<SysUser> user = userService.findById(userCode);
            return user.get();
        } else {
            return new SysUser();
        }
    }

    @RequestMapping(value = {"list"})
    public String list(SysUser user, Model model) {
        model.addAttribute("user", user);
        return "/user/list";
    }




}

package com.dotsite.modules.sys.web;

import com.dotsite.common.lang.StringUtils;
import com.dotsite.modules.conf.controller.BaseController;
import com.dotsite.modules.sys.entity.SysUser;
import com.dotsite.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @ModelAttribute
    public SysUser get(@RequestParam(required = false) String userCode) {
        if (StringUtils.isNotEmpty(userCode)) {
            Optional user = userService.findById(userCode);
            if (user.)
            return;
        } else {
            return new SysUser();
        }
    }

    @RequestMapping(value = {"list"})
    public String list(CloudManageUser user, Model model) {
        model.addAttribute("user", user);
        return "/user/list";
    }

    @RequestMapping(value = {"listData"})
    @ResponseBody
    public String listData(int page, int limit, HttpServletRequest request) {
        Pageable pageable = new PageRequest(page - 1, limit, Sort.Direction.DESC, "addTime");
        Page<CloudManageUser> PageData = userService.findAll(pageable);
        long count = userService.count();
        List<UserTableDto> tableData = PageData.getContent().stream().map(UserTableDto::new).collect(Collectors.toList());
        TableData<PatientTableDto> data = new TableData(count, tableData);
        return JsonMapper.toJsonString(data);
    }

    @RequestMapping(value = {"add"})
    public String add(CloudManageUser user, Model model) {
        model.addAttribute("user", user);
        return "/user/add";
    }

    @RequestMapping(value = {"addpost"}, method = RequestMethod.POST)
    @ResponseBody
    public String addpost(CloudManageUser user, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, user)) {
            throw new RespException("参数不正确");
        }
        CloudManageUser nameUser = userService.findByUsername(user.getUsername());
        if (nameUser != null && StringUtils.isNotEmpty(nameUser.getUid())) {
            throw new RespException("添加用户失败，登录名已存在");
        }
        if (user.getPassword().length() < 6) {
            throw new RespException("添加用户失败，密码不能少于6位");
        }
        userService.add(user);
        return JsonMapper.toJsonString(new RespBean<>(user));
    }

    @RequestMapping(value = {"editpwd"}, method = RequestMethod.GET)
    public String editpwd(CloudManageUser user, Model model) {
        model.addAttribute("user", user);
        return "/user/editpwd";
    }

    @RequestMapping(value = {"editpwdpost"}, method = RequestMethod.POST)
    public String editpwdpost(String username, String password, String newpassword, RedirectAttributes redirectAttributes) throws RespException {
        if (StringUtils.isAnyEmpty(username, password, newpassword)) {
            redirectAttributes.addFlashAttribute("message", "修改密码失败，参数不完整");
            return "redirect:/user/editpwd";
        }
        CloudManageUser user = userService.findByUsername(username);
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            redirectAttributes.addFlashAttribute("修改密码失败，用户信息不存在");
            return "redirect:/user/editpwd";
        }
        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String encodPassword = passwordEncoder.encodePassword(password, user.getSalt());
        if (!StringUtils.equals(user.getPassword(), encodPassword)) {
            redirectAttributes.addFlashAttribute("修改密码失败，原密码不正确");
            return "redirect:/user/editpwd";
        }

        userService.updatePassword(username, password, newpassword);
        redirectAttributes.addFlashAttribute("message", "修改密码成功");
        return "redirect:/user/list";
    }

    @RequestMapping(value = {"editrole"}, method = RequestMethod.GET)
    public String editrole(String uid, Model model) {
        List<CloudManageRole> roleList = roleService.findAll();
        CloudManageUser user = userService.findOne(uid);
        UserEditRoleDto userEditRoleDto = new UserEditRoleDto(roleList, user);
        model.addAttribute("userEditRoleDto", userEditRoleDto);
        return "/user/editrole";
    }

    @RequestMapping(value = {"editrolepost"}, method = RequestMethod.POST)
    @ResponseBody
    public String editrolepost(String uid, String role) throws RespException {
        if (StringUtils.isEmpty(uid)) {
            throw new RespException("分配角色失败，用户编号不能为空");
        }
        userService.editRole(uid, role);
        return JsonMapper.toJsonString(new RespBean());
    }

    @RequestMapping(value = {"delete"})
    public String delete(String uid, RedirectAttributes redirectAttributes) throws RespException, RespPageException {
        if (StringUtils.isEmpty(uid)) {
            redirectAttributes.addFlashAttribute("message", "删除用户失败，用户编号不能为空");
            return "redirect:/user/list";
        }
        CloudManageUser user = userService.findOne(uid);
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            redirectAttributes.addFlashAttribute("message", "删除用户失败，用户不存在");
            return "redirect:/user/list";
        }
        userService.delete(uid);
        redirectAttributes.addFlashAttribute("message", "删除用户成功");
        return "redirect:/user/list";
    }

    @RequestMapping(value = {"resetpwd"})
    @ResponseBody
    public String resetpwd(String uid, RedirectAttributes redirectAttributes) throws RespException, RespPageException {
        if (StringUtils.isEmpty(uid)) {
            throw new RespException("重置密码失败，用户编号不能为空");
        }
        CloudManageUser user = userService.findOne(uid);
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            throw new RespException("重置密码失败，用户不存在");
        }
        userService.resetpwd(uid);
        return JsonMapper.toJsonString(new RespBean<>(user));
    }


}

package com.dotsite.modules.conf.controller;

import com.dotsite.common.beanvalidator.MyBeanValidator;
import com.dotsite.modules.conf.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;

@Controller
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Validator validator;


    protected boolean beanValidator(Model model, BaseEntity baseEntity, Class<?>... groups) {
        try {
            MyBeanValidator.validateWithException(validator, baseEntity, groups);
        } catch (ConstraintViolationException e) {
            List<String> errors = MyBeanValidator.extractPropertyAndMessageAsList(e, ": ");
            model.addAttribute("message", errors);
            return false;
        }
        return true;
    }


    protected boolean beanValidator(BaseEntity baseEntity, Class<?>... groups) {
        try {

            MyBeanValidator.validateWithException(validator, baseEntity, groups);
        } catch (ConstraintViolationException e) {
            return false;
        }
        return true;
    }


}

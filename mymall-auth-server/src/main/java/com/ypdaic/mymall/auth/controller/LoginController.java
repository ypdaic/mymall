package com.ypdaic.mymall.auth.controller;

import com.ypdaic.mymall.auth.vo.UserRegistVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class LoginController {

    @PostMapping("/regist")
    public String regist(@Valid @RequestBody UserRegistVo vo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("errors", errors);
            // 这里不能进行转发，因为请求方式变了
            return "reg.html";
        }

        return "redirect:/login.html";
    }
}

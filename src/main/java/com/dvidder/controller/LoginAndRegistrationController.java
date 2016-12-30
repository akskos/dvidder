/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Account;
import com.dvidder.repository.AccountRepository;
import com.dvidder.service.RegistrationService;
import com.dvidder.validation.RegistrationForm;
import java.io.IOException;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author akseli
 */
@Controller
public class LoginAndRegistrationController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @ModelAttribute
    private RegistrationForm getRegistrationForm() {
        return new RegistrationForm();
    }

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String logout) {
        if (logout != null) {
            SecurityContextHolder.clearContext();
        }
        return "login";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register() {
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String createAccount(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult bindingResult) throws IOException {

        String username = registrationForm.getUsername();
        String password = registrationForm.getPassword();
        MultipartFile profilePicFile = registrationForm.getProfilepic();
        
        if (!registrationService.availableUsername(username)) {
            bindingResult.addError(new FieldError("registrationForm", "username", "username not available"));
        }
        
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        registrationService.registerAccount(username, password, profilePicFile);
        
        return "redirect:/login";
    }

}

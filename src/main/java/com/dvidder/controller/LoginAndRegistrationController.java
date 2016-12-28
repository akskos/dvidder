/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Account;
import com.dvidder.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author akseli
 */
@Controller
public class LoginAndRegistrationController {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String createAccount(@RequestParam String username, @RequestParam String password) {
        
        if (accountRepository.findByUsername(username) != null) {
            return "redirect:/register?error";
        }
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
        return "redirect:/login";
    }

}

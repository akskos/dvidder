/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Account;
import com.dvidder.domain.Post;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.PostRepository;
import com.dvidder.service.AccountService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akseli
 */
@Controller
public class AccountController { 
   
    @Autowired
    private AccountService accountService;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @RequestMapping(value="/account", method=RequestMethod.GET)
    @ResponseBody
    public String getAccountInfo(@RequestParam String param) {
        return accountService.getAccountInfo(param);
    }
    
    @RequestMapping(value="/account/{username}", method=RequestMethod.DELETE, produces="application/json")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        
        if (accountService.deleteAccount(username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value="/account/{username}/profilepic", method=RequestMethod.GET, produces="image/png")
    @ResponseBody
    public byte[] getProfilePicture(@PathVariable String username) throws IOException {
        if (!accountService.accountExists(username)) {
            return new byte[0];
        }
        return accountService.getProfilePictureForUsername(username);
    }
}

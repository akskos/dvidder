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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akseli
 */
@Controller
public class AccountController { 
   
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @RequestMapping(value="/account", method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Account account() {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByUsername(currentUser.getUsername());
        return account;
    }
    
    @RequestMapping(value="/account/{username}", method=RequestMethod.DELETE, produces="application/json")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Delete all account's posts
        for (Post p : account.getPosts()) {
            postRepository.delete(p);
        }
        
        accountRepository.delete(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

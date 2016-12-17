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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akseli
 */
@Controller
public class DefaultController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PostRepository postRepository;

    @RequestMapping("/")
    public String index(Model model) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", currentUser.getUsername());
        return "index";
    }
    
    // Create a post
    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String post(@RequestParam String content) {
        
        // Find current account
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account currentAccount = accountRepository.findByUsername(currentUser.getUsername());
        
        // Create the post
        Post post = new Post();
        post.setContent(content);
        post.setOwner(currentAccount);
        postRepository.save(post);
        
        return "redirect:/";
    }
}

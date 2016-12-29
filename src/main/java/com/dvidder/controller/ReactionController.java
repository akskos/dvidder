/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Post;
import com.dvidder.repository.PostRepository;
import com.dvidder.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReactionController {
    
    @Autowired
    private ReactionService reactionService;
    
    @RequestMapping(value="/posts/{id}/like", method=RequestMethod.POST)
    @ResponseBody
    public Post like(@PathVariable String id) {
        return reactionService.like(id);
    }
    
    @RequestMapping(value="/posts/{id}/dislike", method=RequestMethod.POST)
    @ResponseBody
    public Post dislike(@PathVariable String id) {
        return reactionService.dislike(id);
    }
}

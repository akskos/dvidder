/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Post;
import com.dvidder.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akseli
 */
public class PostController {
    
    @Autowired
    PostService postService;
    
    @RequestMapping(value="/posts", method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Post> listPosts(@RequestParam(required=false) String username, @RequestParam(required=false) String tag) {
        if (username != null) {
            return postService.getPostsByUser(username);
        } else {
            return postService.getPostsByTag(tag);
        }
    }
    
    // Create a post
    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String post(@RequestParam String content, @RequestParam String tags) {
        postService.createPost(content, tags);
        return "redirect:/";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Post;
import com.dvidder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author akseli
 */
@Service
public class ReactionService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ProfileService profileService;
    
    public Post like(String id) {
        Post post = postRepository.findOne(Long.parseLong(id));
        String username = profileService.getCurrentUsername();
        
        if (!post.getLikers().contains(username)) {
            post.getLikers().add(username);
            postRepository.save(post);
        }
        
        return post;
    }
}

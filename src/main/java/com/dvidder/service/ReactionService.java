/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Dislike;
import com.dvidder.domain.Like;
import com.dvidder.domain.Post;
import com.dvidder.domain.Reaction;
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
        
        for (Reaction r : post.getReactions()) {
            if (r.getReactor().equals(username) && r.getReactionName().equals("like")) {
                return post;
            }
        }
        
        Like like = new Like();
        like.setReactor(username);
        post.getReactions().add(like);
        
        return post;
    }

    public Post dislike(String id) {
        Post post = postRepository.findOne(Long.parseLong(id));
        String username = profileService.getCurrentUsername();
        
        for (Reaction r : post.getReactions()) {
            if (r.getReactor().equals(username) && r.getReactionName().equals("dislike")) {
                return post;
            }
        }
        
        Dislike dislike = new Dislike();
        dislike.setReactor(username);
        post.getReactions().add(dislike);
        
        return post;
    }
}

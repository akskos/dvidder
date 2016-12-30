/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Post;
import com.dvidder.domain.Reaction;
import com.dvidder.repository.PostRepository;
import com.dvidder.repository.ReactionRepository;
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
    private ReactionRepository reactionRepository;

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

        Reaction like = new Reaction("like");
        like.setReactor(username);
        post.getReactions().add(like);

        reactionRepository.save(like);
        postRepository.save(post);

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

        Reaction dislike = new Reaction("dislike");
        dislike.setReactor(username);
        post.getReactions().add(dislike);

        reactionRepository.save(dislike);
        postRepository.save(post);

        return post;
    }
}

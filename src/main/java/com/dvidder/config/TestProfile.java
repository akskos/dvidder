/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.config;

import com.dvidder.domain.Account;
import com.dvidder.domain.Post;
import com.dvidder.domain.Tag;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.PostRepository;
import com.dvidder.repository.TagRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author akseli
 */
@Component
@Profile("test")
public class TestProfile {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    public void init() {
        
        // Create admin account
        Account admin = new Account();
        admin.setAdmin(true);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("supersecret"));
        accountRepository.save(admin);
        
        // Create test account
        Account user1 = new Account();
        user1.setUsername("test_user");
        user1.setPassword(passwordEncoder.encode("123456"));
        
        // Create post for the test account
        Post post = new Post();
        post.setContent("this is a post");
        post.setSender("test_user");
        post.setDate(new Date());
        List<Tag> tags = Arrays.asList(new Tag("a"), new Tag("b"), new Tag("c"));
        for (Tag t : tags) {
            tagRepository.save(t);
        }
        
        post.setTags(tags);
        user1.getPosts().add(post);
        
        postRepository.save(post);
        accountRepository.save(user1);
        
        // Create another test account
        Account user2 = new Account();
        user2.setUsername("test_user_2");
        user2.setPassword(passwordEncoder.encode("password"));
        accountRepository.save(user2);
    }
}

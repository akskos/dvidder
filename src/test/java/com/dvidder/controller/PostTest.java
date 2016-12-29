package com.dvidder.controller;


import com.dvidder.domain.Account;
import com.dvidder.domain.Post;
import com.dvidder.domain.Tag;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.PostRepository;
import com.dvidder.repository.TagRepository;
import com.dvidder.service.PostService;
import com.dvidder.service.PostService;
import com.dvidder.service.ProfileService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akseli
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PostTest {
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void getPostsForUser() throws Exception {
        mockMvc.perform(get("/posts?username=test_user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("this is a post"));
    }
    
    @Test
    public void getPostsForTag() throws Exception {
        mockMvc.perform(get("/posts?tag=b"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("this is a post"));
    }
    
    @Test
    @WithUserDetails("test_user")
    public void createPost() throws Exception {
        mockMvc.perform(post("/post?content=Hello&tags=a b"));
        mockMvc.perform(get("/posts?username=test_user"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content").value("Hello"));
    }
    
    @Test
    @WithUserDetails("test_user")
    public void deletePost() throws Exception {
        
        // Create a new post to be deleted
        mockMvc.perform(post("/post?content=ads&tags=qwer"));
        
        String postResponse = mockMvc.perform(get("/posts?tag=qwer"))
                .andReturn().getResponse().getContentAsString();
        JSONArray postObjectList = new JSONArray(postResponse);
        JSONObject postObject = (JSONObject) postObjectList.get(0);
        String postId = Long.toString(postObject.getLong("postId"));
        
        // Delete the post
        mockMvc.perform(delete("/post/" + postId))
                .andExpect(status().isOk());
    }
}

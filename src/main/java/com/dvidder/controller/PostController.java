/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.controller;

import com.dvidder.domain.Post;
import com.dvidder.domain.Tag;
import com.dvidder.service.PostService;
import com.dvidder.validation.PostForm;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class PostController {

    @Autowired
    PostService postService;

    @ModelAttribute
    private PostForm getPostForm() {
        return new PostForm();
    }

    // List posts
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Post> listPosts(@RequestParam(required = false) String username, @RequestParam(required = false) String tag) {
        if (username != null) {
            return postService.getPostsByUser(username);
        } else {
            return postService.getPostsByTag(tag);
        }
    }

    // Create a post
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) {

        String content = postForm.getContent();
        String tags = postForm.getTags();

        // Validate tags
        if (!postService.validNumberOfTags(tags)) {
            bindingResult.addError(new FieldError("postForm", "tags", "no more than 4 tags per dveed"));
        }
        if (!postService.validTags(tags)) {
            bindingResult.addError(new FieldError("postForm", "tags", "tags can't be over 10 characters long"));
        }

        if (bindingResult.hasErrors()) {
            return "index";
        }

        postService.createPost(content, tags);

        return "redirect:/";
    }

    // Delete a post
    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        if (postService.deletePost(id)) {
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
    }
}

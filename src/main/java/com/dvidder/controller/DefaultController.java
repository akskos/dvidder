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
import com.dvidder.service.PostService;
import com.dvidder.validation.PostForm;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @ModelAttribute
    private PostForm getPostForm() {
        return new PostForm();
    }

    @RequestMapping("/")
    public String index(Model model) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", currentUser.getUsername());
        return "index";
    }
}

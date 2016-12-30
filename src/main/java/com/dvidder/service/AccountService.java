/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Account;
import com.dvidder.domain.Post;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.PostRepository;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author akseli
 */
@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PostRepository postRepository;
    
    @Transactional
    public byte[] getProfilePictureForUsername(String username) throws IOException {
        Account account = accountRepository.findByUsername(username);
        if (account.getProfilePicture().isDefaultPic()) {
            
            byte[] imgData = new byte[5000];
            Resource resource = new ClassPathResource("static/img/default-profile-pic.png");
            if (!resource.exists()) {
                System.out.println("RESOURCE DOESN'T EXIST");
            } else {
                System.out.println("YES, RESOURCE EXISTS");
                
                System.out.println("resource content length: " + resource.contentLength());
            }
            
            imgData = IOUtils.toByteArray(resource.getInputStream());
            System.out.println("byte array length: " + imgData.length);
            return imgData;
        }
        return accountRepository.findByUsername(username).getProfilePicture().getImageData();
    }
    
    public boolean accountExists(String username) {
        if (accountRepository.findByUsername(username) != null) {
            return true;
        }
        return false;
    }
    
    public String getAccountInfo(String param) {
        Account account = accountRepository.findByUsername(profileService.getCurrentUsername());
        
        if (param.equals("username")) {
            return account.getUsername();
        } else if (param.equals("admin")) {
            return Boolean.toString(account.isAdmin());
        } else {
            return "error";
        }
    }

    public boolean deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) {
            return false;
        }
        
        // Delete all account's posts
        for (Post p : account.getPosts()) {
            postRepository.delete(p);
        }
        
        accountRepository.delete(account);
        
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Account;
import com.dvidder.domain.ProfilePicture;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.ProfilePictureRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author akseli
 */
@Service
public class RegistrationService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private ProfilePictureRepository profilePictureRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void registerAccount(String username, String password, MultipartFile profilePicFile) throws IOException {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        
        ProfilePicture profilepic = new ProfilePicture();
        profilepic.setDefaultPic(false);
        profilepic.setImageData(profilePicFile.getBytes());
        account.setProfilePicture(profilepic);
        
        profilePictureRepository.save(profilepic);
        accountRepository.save(account);
    }
    
    public boolean availableUsername(String username) {
        if (accountRepository.findByUsername(username) != null) {
            return false;
        }
        return true;
    }
}

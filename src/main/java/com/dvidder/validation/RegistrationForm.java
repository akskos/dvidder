/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.validation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author akseli
 */
public class RegistrationForm {

    @NotEmpty
    @Length(min = 1, max = 40)
    private String username;

    @NotEmpty
    @Length(min = 6, max = 100)
    private String password;
    
    private MultipartFile profilepic;
    
    private boolean defaultPicture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(MultipartFile profilepic) {
        this.profilepic = profilepic;
    }

    public boolean isDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(boolean defaultPicture) {
        this.defaultPicture = defaultPicture;
    }
}

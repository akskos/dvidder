/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author akseli
 */
@Entity
public class ProfilePicture {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long profilePictureId;
    
    @Lob
    private byte[] imageData;
    
    private boolean defaultPic;

    public Long getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(Long profilePictureId) {
        this.profilePictureId = profilePictureId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isDefaultPic() {
        return defaultPic;
    }

    public void setDefaultPic(boolean defaultPic) {
        this.defaultPic = defaultPic;
    }
}

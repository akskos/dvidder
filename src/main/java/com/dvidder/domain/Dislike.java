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

/**
 *
 * @author akseli
 */
@Entity
public class Dislike extends Reaction {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long disLikeId;

    public Long getDisLikeId() {
        return disLikeId;
    }

    public void setDisLikeId(Long disLikeId) {
        this.disLikeId = disLikeId;
    }
    
    public Dislike() {
        super("dislike");
    }
}

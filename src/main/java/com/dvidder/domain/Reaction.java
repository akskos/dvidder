/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.domain;

/**
 *
 * @author akseli
 */
public class Reaction {
    
    private String reactor;
    private String reactionName;
    
    public Reaction(String name) {
        reactionName = name;
    }

    public String getReactionName() {
        return reactionName;
    }

    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }
    
    public String getReactor() {
        return reactor;
    }

    public void setReactor(String reactor) {
        this.reactor = reactor;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.repository;

import com.dvidder.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author akseli
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    
}

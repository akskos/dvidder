/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.repository;

import com.dvidder.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author akseli
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    
}

package com.colak.springtutorial.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("mySecurityService")
public class MySecurityService {

    public boolean canEditUser(String userId) {
        UserDetails currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUsername().equals(userId);
    }
}

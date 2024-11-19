package com.colak.springtutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "adminController")
@RequestMapping("/api/v1")
public class AdminController {

    // http://localhost:8080/api/v1/secured/admin
    @GetMapping(value = "/secured/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedCall() {
        return "hello world secured!!";
    }

    // http://localhost:8080/api/v1/secured/admin/adminstrator
    @GetMapping(value = "/secured/admin/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') and @mySecurityService.canEditUser(#userId)")
    public String securedForAdminCall(@PathVariable String userId) {
        return "hello world secured!! for " + userId;
    }

    // Spring 3.3 style
    // http://localhost:8080/api/v1/secured/admin2/administrator
    @GetMapping(value = "/secured/admin2/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') and (#userId == @adminController.getCurrentUser())")
    public String securedForAdminCall2(@PathVariable String userId) {
        return "hello world secured!! for " + userId;
    }

    // Do not delete this method. It is used by Spel in this controller
    public String getCurrentUser() {
        UserDetails currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUsername();
    }

}

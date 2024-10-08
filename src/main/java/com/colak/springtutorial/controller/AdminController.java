package com.colak.springtutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public String securedForAdminAndUserCall(@PathVariable String userId) {
        return "hello world secured!! for " + userId;
    }

}
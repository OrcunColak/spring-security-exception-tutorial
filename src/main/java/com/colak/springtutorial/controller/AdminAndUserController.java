package com.colak.springtutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminAndUserController {

    // http://localhost:8080/api/v1/secured/authenticateduser/user
    @GetMapping(value = "/secured/authenticateduser/{resourceId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER)")
    public String securedForAdminAndUserCall(@PathVariable String resourceId) {
        return "hello world secured!! for " + resourceId;
    }

}

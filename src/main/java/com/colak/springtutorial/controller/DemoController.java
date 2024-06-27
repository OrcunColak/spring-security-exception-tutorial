package com.colak.springtutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    // http://localhost:8080/api/v1/secured/hello-world
    @GetMapping(value = "/secured/hello-world")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedCall() {
        return "hello world secured!!";
    }

    // http://localhost:8080/api/v1/secured/hello-world/user
    @GetMapping(value = "/secured/hello-world/{resourceId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' ,'ROLE_USER') and @myCustomSecurityService.isResourceOwner(authentication, #resourceId)")
    public String securedForAdminAndUserCall(@PathVariable String resourceId) {
        return "hello world secured!! for " + resourceId;
    }

    // http://localhost:8080/api/v1/hello-world
    @GetMapping(value = "/hello-world")
    public String unsecuredCall() {
        return "hello world!!";
    }

}

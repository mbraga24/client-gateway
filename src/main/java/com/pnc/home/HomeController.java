package com.pnc.home;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home-controller")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> welcomeHome() {
        return ResponseEntity.ok("Welcome to PNC");
    }
}

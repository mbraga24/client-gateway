package com.pnc.home;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Public Home page! You can see this.");
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeHome() {
        return ResponseEntity.ok("Welcome home! Only when authenticated.");
    }
}

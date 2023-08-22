package com.pnc.publicweb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Public Home page! You can see this.");
    }

}

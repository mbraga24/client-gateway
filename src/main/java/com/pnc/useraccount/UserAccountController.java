package com.pnc.useraccount;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class UserAccountController {

    @GetMapping
    public ResponseEntity<String> accountPage() {
        return ResponseEntity.ok("Welcome to PNC!");
    }

}

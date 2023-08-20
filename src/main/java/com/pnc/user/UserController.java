package com.pnc.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/pnc")
public class UserController {

    private final UserService appUserService;

    @GetMapping
    public List<User> getAppUsers() {
        return appUserService.getAllAppUsers();
    }

}

package com.pnc.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserDAO userDAO;

    public List<User> getAllAppUsers() {
        return userDAO.selectAllAppUsers();
    }

}

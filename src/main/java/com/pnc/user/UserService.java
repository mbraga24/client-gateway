package com.pnc.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method just to test UserNotFoundException when user is not found
    //    public Optional<User> findUserTest(String email) {
    //        return Optional.empty();
    //    }

}

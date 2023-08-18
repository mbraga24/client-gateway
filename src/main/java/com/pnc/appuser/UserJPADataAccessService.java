package com.pnc.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserJPADataAccessService implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public List<User> selectAllAppUsers() {
        return userRepository.findAll();
    }

}

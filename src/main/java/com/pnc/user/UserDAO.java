package com.pnc.user;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    boolean isEmailTaken(String email);

}

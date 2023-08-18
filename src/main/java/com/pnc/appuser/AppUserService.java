package com.pnc.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//public class AppUserService {
@AllArgsConstructor
@Service
public class AppUserService {

    private final AppUserDAO appUserDAO;

    public List<AppUser> getAllAppUsers() {
        return appUserDAO.selectAllAppUsers();
    }


}

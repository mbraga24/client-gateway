package com.pnc.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class AppUserJPADataAccessService implements AppUserDAO {

    private final AppUserRepository appUserRepository;

    @Override
    public List<AppUser> selectAllAppUsers() {
        return appUserRepository.findAll();
    }

}

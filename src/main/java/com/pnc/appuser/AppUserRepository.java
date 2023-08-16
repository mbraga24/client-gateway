package com.pnc.appuser;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);
}

package com.pnc.appuser;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Transactional
//@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

//    Optional<AppUser> findByEmail(String email);
}

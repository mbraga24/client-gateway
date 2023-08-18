package com.pnc.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

//@Transactional
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<AppUser> findByEmail(String email);
}

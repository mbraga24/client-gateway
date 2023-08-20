package com.pnc.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Transactional
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

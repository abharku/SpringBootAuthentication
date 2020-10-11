package com.bluenimbus.spring.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluenimbus.spring.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find by username user.
   *
   * @param username the username
   * @return the user
   */
  User findByUsername(String username);
  boolean existsByUsername(String username);
}


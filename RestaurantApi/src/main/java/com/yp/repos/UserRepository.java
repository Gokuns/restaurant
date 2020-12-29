package com.yp.repos;

import com.yp.entity.Authority;
import com.yp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM USERS u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    Set<User> findAllByAuthorities(Authority authority);
}

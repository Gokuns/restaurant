package com.yp.repos;

import com.yp.entity.Authority;
import com.yp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {


}

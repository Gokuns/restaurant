package com.yp.repos;

import com.yp.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    @Query("SELECT DISTINCT authority FROM AUTHORITIES")
    List<String> findDistinctRoles();
}

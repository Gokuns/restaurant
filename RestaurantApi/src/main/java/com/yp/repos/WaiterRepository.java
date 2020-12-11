package com.yp.repos;

import com.yp.entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository <Waiter, Long> {
}

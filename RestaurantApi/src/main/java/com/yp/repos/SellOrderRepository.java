package com.yp.repos;

import com.yp.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
}

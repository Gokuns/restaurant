package com.yp.repos;

import com.yp.model.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Integer> {
}

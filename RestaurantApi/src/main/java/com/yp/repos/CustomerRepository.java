package com.yp.repos;

import com.yp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c from CUSTOMERS c")
    Page<Customer> findAllPages(Pageable pageable);

    @Query("SELECT c from CUSTOMERS c")
    Slice<Customer> findAllSlices(Pageable pageable);
}

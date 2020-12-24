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

    @Query("Select c from Customer c")
    public Page<Customer> findAllPages(Pageable pageable);

    @Query("Select c from Customer c")
    public Slice<Customer> findAllSlices(Pageable pageable);
}

package com.yp.repos;

import com.yp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    @Query("SELECT u FROM Product u WHERE u.category = :category")
//    List<Product> findAllWithCategory(@Param("category") String category);
//
//    @Query("SELECT DISTINCT category FROM Product")
//    List<String> findDistinctCategories();


}

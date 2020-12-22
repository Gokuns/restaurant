package com.yp.repos;

import com.yp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    @Query("SELECT u FROM Product u WHERE u.category = :category")
//    List<Product> findAllWithCategory(@Param("category") String category);
//
//    @Query("SELECT DISTINCT category FROM Product")
//    List<String> findDistinctCategories();

    @Query("SELECT p FROM Product  AS p INNER JOIN PRODUCT_CATEGORIES AS pc ON p.id=pc.product_id INNER JOIN Category AS c ON pc.category_id=c.id WHERE c.id= :cat_id")
    Set<Product> findAllWithCategory(@Param("cat_id") int cat_id);


}

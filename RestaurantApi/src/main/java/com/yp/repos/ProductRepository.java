package com.yp.repos;

import com.yp.entity.Category;
import com.yp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


   List<Product> findAllByCategoriesIn(List<Category> categories);

   Slice<Product> findAllByCategories(Category category, Pageable pageable);

   @Query("SELECT p from PRODUCTS p")
   Page<Product> findAllPages(Pageable pageable);

   //@Query("Select p from Product p ")

   @Query("SELECT p from PRODUCTS p")
   Slice<Product> findAllSlice(Pageable pageable) ;


}

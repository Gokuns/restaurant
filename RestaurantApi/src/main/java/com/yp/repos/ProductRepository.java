package com.yp.repos;

import com.yp.entity.Category;
import com.yp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


   List<Product> findAllByCategoriesIn(List<Category> categories);

   Slice<Product> findAllByCategories(Category category, Pageable Pageable);

   @Query("Select p from Product p")
   Page<Product> findAllPages(Pageable pageable);

   //@Query("Select p from Product p ")

   @Query("Select p from Product p")
   Slice<Product> findAllSlice(Pageable pageable) ;

//   @Query(value = "Select u from Product u inner join u.categories c where u.category_id=:cat_id")
//   List<Product> findAllByCategoryId(@Param(value = "cat_id")int cat_id);


}

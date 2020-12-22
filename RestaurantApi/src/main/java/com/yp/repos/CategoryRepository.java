package com.yp.repos;

import com.yp.entity.Category;
import com.yp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

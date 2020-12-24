package com.yp.repos;

import com.yp.entity.TableCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableCategoryRepository extends JpaRepository<TableCategory, Long> {
}

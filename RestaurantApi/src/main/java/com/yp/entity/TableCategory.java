package com.yp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@SQLDelete(
        sql="UPDATE CATEGORIES SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity(name = "TABLE_CATEGORIES")
@Data
@NoArgsConstructor
public class TableCategory extends BaseEntity{
    private String name;
    private int number;
}

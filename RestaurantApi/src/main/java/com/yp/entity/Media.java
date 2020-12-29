package com.yp.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE MEDIAS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "MEDIAS")
@Data
@NoArgsConstructor
public class Media extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "FILE_CONTENT",length = 100000)
    private byte[] fileContent;
}

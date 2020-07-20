package com.msheviakou.bundlejwt.role;

import com.msheviakou.bundlejwt.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;
}

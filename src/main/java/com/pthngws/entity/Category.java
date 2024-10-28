package com.pthngws.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Cate")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name", length = 200,columnDefinition = "nvarchar(200)")
    @NotEmpty(message = "Tên danh mục không được để trống")
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;
}
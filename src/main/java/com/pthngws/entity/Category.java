package com.pthngws.entity;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "Categorie")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(name = "category_name", length = 200, columnDefinition = "nvarchar(200) not null")
    private String name;

    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL)
    private Set<Product> products;
}

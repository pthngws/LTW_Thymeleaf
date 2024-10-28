package com.pthngws.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name = "product_name", length = 500, columnDefinition = "nvarchar(500) not null")
    private String name;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private double unitPrice;
    @Column(length = 200)
    private String images;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double discount;
    @Column(nullable = false)
    private Date createDate;
    @Column(nullable = false)
    private short status;

    @ManyToOne
    @JoinColumn(name = "categotyId")
    private Category category;



}

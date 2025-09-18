package org.example.baitaptuan5_17_09.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false) // foreign key
    private Category category;

    @Column(name = "name", length = 200, columnDefinition = "NVARCHAR(200) NOT NULL")
    private String name;

    @Column(name = "image")
    private String image;
}

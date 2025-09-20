package org.example.baitaptuan5_16_9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Videos")
public class Video implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable=false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "description", length = 255, columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(name = "url", nullable=false)
    private String url;
}

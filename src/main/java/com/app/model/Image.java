package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    private Integer size;

    @Column(length = 300)
    private String link;

    @OneToOne
    @JoinTable(
            name = "cat_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private Cat cat;
}

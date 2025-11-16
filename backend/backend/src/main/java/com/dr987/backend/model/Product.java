package com.dr987.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", nullable = false)
  private String name;

  @Column(name = "precio", nullable = false)
  private Double price;

  @Column(name = "image_url")
  private String imageUrl;

  // Constructor por defecto (requerido por JPA)
  public Product() {
  }

  // Constructor con parámetros
  public Product(String name, Double price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}


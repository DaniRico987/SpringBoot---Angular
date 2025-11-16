package com.dr987.backend.service;

import com.dr987.backend.model.Product;
import com.dr987.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Encuentra todos los productos
   * 
   * @return Lista de todos los productos
   */
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  /**
   * Encuentra un producto por su ID
   * 
   * @param id El ID del producto a buscar
   * @return Optional que contiene el producto si existe, o vacío si no existe
   */
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  /**
   * Guarda un producto (crea uno nuevo o actualiza uno existente)
   * 
   * @param product El producto a guardar
   * @return El producto guardado
   */
  public Product save(Product product) {
    return productRepository.save(product);
  }

  /**
   * Elimina un producto por su ID
   * 
   * @param id El ID del producto a eliminar
   */
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }
}


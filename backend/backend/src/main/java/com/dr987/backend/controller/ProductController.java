package com.dr987.backend.controller;

import com.dr987.backend.model.Product;
import com.dr987.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Obtiene todos los productos
   * GET /api/productos
   * 
   * @return Lista de todos los productos
   */
  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.findAll();
    return ResponseEntity.ok(products);
  }

  /**
   * Obtiene un producto por su ID
   * GET /api/productos/{id}
   * 
   * @param id El ID del producto
   * @return El producto si existe, o 404 si no existe
   */
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.findById(id)
        .map(product -> ResponseEntity.ok(product))
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Crea un nuevo producto
   * POST /api/productos
   * 
   * @param product El producto a crear
   * @return El producto creado con código 201
   */
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product savedProduct = productService.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
  }

  /**
   * Actualiza un producto existente
   * PUT /api/productos/{id}
   * 
   * @param id               El ID del producto a actualizar
   * @param productDetails Los nuevos detalles del producto
   * @return El producto actualizado, o 404 si no existe
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
    return productService.findById(id)
        .map(existingProduct -> {
          existingProduct.setName(productDetails.getName());
          existingProduct.setPrice(productDetails.getPrice());
          existingProduct.setImageUrl(productDetails.getImageUrl());
          Product updatedProduct = productService.save(existingProduct);
          return ResponseEntity.ok(updatedProduct);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Elimina un producto por su ID
   * DELETE /api/productos/{id}
   * 
   * @param id El ID del producto a eliminar
   * @return 204 si se eliminó correctamente, o 404 si no existe
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    return productService.findById(id)
        .map(product -> {
          productService.deleteById(id);
          return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
  }
}


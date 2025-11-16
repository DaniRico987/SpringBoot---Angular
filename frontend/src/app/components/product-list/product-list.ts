import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.interface';
import { CommonModule } from '@angular/common';
import { ProductCardComponent } from '../product-card/product-card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, ProductCardComponent],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        console.error('Error fetching products:', err);
      },
    });
  }

  onDeleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe({
      next: () => {
        // Recargar la lista de productos después de eliminar
        this.loadProducts();
      },
      error: (err) => {
        console.error('Error deleting product:', err);
        alert('Error al eliminar el producto. Por favor, intenta nuevamente.');
      },
    });
  }

  onEditProduct(productId: number): void {
    this.router.navigate(['/edit', productId]);
  }

  navigateToCreate() {
    this.router.navigate(['/create']);
  }
}

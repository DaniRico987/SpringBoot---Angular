import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css',
})
export class ProductFormComponent implements OnInit {
  productForm!: FormGroup;
  productId: number | null = null;
  isEditMode: boolean = false;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.min(0.01)]],
      imageUrl: ['', [Validators.required]],
    });

    // Verificar si estamos en modo edición
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.productId = +id;
      this.isEditMode = true;
      this.loadProduct(this.productId);
    }
  }

  loadProduct(id: number): void {
    this.productService.getProductById(id).subscribe({
      next: (product) => {
        this.productForm.patchValue({
          name: product.name,
          price: product.price,
          imageUrl: product.imageUrl,
        });
      },
      error: (err) => {
        console.error('Error loading product:', err);
        alert('Error al cargar el producto');
        this.router.navigate(['/list']);
      },
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const productData = {
        name: this.productForm.value.name,
        price: parseFloat(this.productForm.value.price),
        imageUrl: this.productForm.value.imageUrl,
      };

      if (this.isEditMode && this.productId) {
        // Actualizar producto existente
        this.productService.updateProduct(this.productId, productData).subscribe({
          next: (response) => {
            alert('Producto actualizado correctamente');
            this.navigateToList();
          },
          error: (err) => {
            alert('Error al actualizar el producto');
            console.error('Error:', err);
          },
        });
      } else {
        // Crear nuevo producto
        this.productService.createProduct(productData).subscribe({
          next: (response) => {
            this.productForm.reset();
            alert('Producto creado correctamente');
            this.navigateToList();
          },
          error: (err) => {
            alert('Error al crear el producto');
            console.error('Error:', err);
          },
        });
      }
    } else {
      // Marcar todos los campos como touched para mostrar errores
      Object.keys(this.productForm.controls).forEach((key) => {
        this.productForm.get(key)?.markAsTouched();
      });
    }
  }

  get name() {
    return this.productForm.get('name');
  }

  get price() {
    return this.productForm.get('price');
  }

  get imageUrl() {
    return this.productForm.get('imageUrl');
  }

  navigateToList() {
    this.router.navigate(['/list']);
  }
}

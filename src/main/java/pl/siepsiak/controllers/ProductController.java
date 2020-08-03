package pl.siepsiak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siepsiak.model.Product;
import pl.siepsiak.services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.listAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.get(id);
            return ResponseEntity.ok(product);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public void addProduct(@RequestBody Product product) {
        productService.save(product);
    }


    @PutMapping(path = "/products/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
                                                 @PathVariable Long id) {
        try {
            Product currentProduct = productService.get(id);
            if (product.getName().isEmpty()) {
                currentProduct.setName(currentProduct.getName());
            } else {
                currentProduct.setName(product.getName());
            }
            if (product.getPrice() == null) {
                currentProduct.setPrice(currentProduct.getPrice());
            } else {
                currentProduct.setPrice(product.getPrice());
            }
            productService.save(currentProduct);
            return ResponseEntity.ok(currentProduct);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

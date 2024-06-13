package com.online.ecommerce.controller;

import com.online.ecommerce.model.Product;
import com.online.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addAll")
    public ResponseEntity<Object> saveProducts(@RequestBody List<Product> products){
        if(!products.isEmpty())
            return productService.saveProducts(products);
        return new ResponseEntity<>("Request is empty", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveOneProduct(@RequestBody Product product){
        return productService.saveOneProduct(product);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/getByCode")
    public ResponseEntity<Object> getProductByCode(@RequestParam int productCode){
        return productService.getProductByCode(productCode);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }
}

package com.online.ecommerce.service;

import com.online.ecommerce.model.Product;
import com.online.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    public ResponseEntity<Object> saveProducts(List<Product> products) {
        productRepo.saveAll(products);
        return new ResponseEntity<>("Products added...", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> saveOneProduct(Product product) {
        if(productRepo.findByProductCode(product.getProductCode()) == null){
            productRepo.save(product);
            return new ResponseEntity<>("One Product added...", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product code must be unique...",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getProducts() {

        if (!productRepo.findAll().isEmpty()){
            return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Products found...",HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Object> getProductByCode(int productCode) {
        Product product = productRepo.findByProductCode(productCode);
        if(product != null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product Not Found...",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteProduct(int id) {
        if(productRepo.findById(id).isPresent()){
            productRepo.deleteById(id);
            return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateProduct(int id,Product product) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setPrice(product.getPrice());
            productRepo.save(existingProduct);
            return new ResponseEntity<>(existingProduct,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

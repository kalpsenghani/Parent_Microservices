package ca.gbc.productservice.controller;


import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller, allowing it to handle HTTP requests and respond with JSON or XML.
@RequestMapping("/api/product") // Base URL for all endpoints in this controller. Requests to "/api/product" will be mapped here.
@RequiredArgsConstructor
// Lombok annotation that generates a constructor with required fields (final fields)
public class ProductController {

    private final ProductService productService;

    @PostMapping// Maps HTTP POST requests to this method. Typically used for creating new resources.
    @ResponseStatus(HttpStatus.CREATED)// Sets the response status to 201 Created when a product is successfully created.
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse createdProduct = productService.createProduct(productRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/product/" + createdProduct.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdProduct);
    }

    @GetMapping// Maps HTTP GET requests to this method. Typically used to retrieve resources.
    @ResponseStatus(HttpStatus.OK)// Sets the response status to 200 OK when the products are successfully retrieved.
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    // http://localhost:8080/apu/product/klfndkfjbnon
    @PutMapping("/{productId}") // Maps HTTP PUT requests to this method. The (productId) is a path variable used to identify which product to
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    ////ResponseEntity's?> is used when you want to return an HTTP response without specifying the type of the response body, meaning that the response
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId,
                                           @RequestBody ProductRequest productRequest){

        String updatedProductId = productService.updateProduct(productId, productRequest);

        // set the location header attribute
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/product" + updatedProductId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{productId}")// Maps HTTP DELETE requests to this method to delete a product identified by its ID
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId){

        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
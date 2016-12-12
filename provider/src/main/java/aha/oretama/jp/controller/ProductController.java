package aha.oretama.jp.controller;

import aha.oretama.jp.model.Product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */

@RestController
public class ProductController {

    List<Product> products = Arrays.asList(
        new Product("P001", "product1", "http://product1"),
        new Product("P002", "product2", "http://product2"),
        new Product("P003", "product3", "http://product3"));


    @GetMapping(path = "/api/v1/products/{id:P[0-9]{3}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable String id) throws IllegalArgumentException {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No product: id " + id));
    }

}

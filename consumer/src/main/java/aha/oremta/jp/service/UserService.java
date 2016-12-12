package aha.oremta.jp.service;

import aha.oremta.jp.model.Product;
import aha.oremta.jp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */
@Service
public class UserService {

    private final RestTemplate restTemplate;
    private static final String PORT = "8080";

    private List<User> USERS = Arrays.asList(
        new User("001","yamada taro","taro@gmail.com",Arrays.asList(new Product("P001"),new Product("P002")))
        ,new User("002","yamada hanako","hanako@gmail.com",Arrays.asList(new Product("P004"),new Product("P005")))
        ,new User("003","yamada junko","junko@gmail.com",Arrays.asList(new Product("X001")))
    );

    @Autowired
    public UserService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public User getUser(String userId) {

        List<User> users = getInnerUser(userId);
        if(users.isEmpty() || users.size() != 1){
            throw new IllegalArgumentException("No user or Double user. userId: " + userId);
        }

        User user = users.stream().findFirst().get();
        user.setProducts(getProducts(
            user.getProducts().stream()
                .map(product -> product.getId())
                .collect(Collectors.toList())));

        return user;
    }

    private List<User> getInnerUser(String userId) {
        return USERS.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
    }

    private List<Product> getProducts(List<String> productIds) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        return productIds.stream().map(productId -> {
            UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:" + PORT + "/api/v1/products/" + productId);


            ResponseEntity<Product> response = restTemplate
                .exchange(builder.build().encode().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Product.class);

            return response.getBody();
        }).collect(Collectors.toList());
    }
}

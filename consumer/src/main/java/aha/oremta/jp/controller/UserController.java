package aha.oremta.jp.controller;

import aha.oremta.jp.model.User;
import aha.oremta.jp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "api/v1/users/{userId:[0-9]{3}}")
    public User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }
}

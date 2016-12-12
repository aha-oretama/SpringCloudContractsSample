package aha.oretama.jp.controller;

import aha.oretama.jp.model.Answer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */

@RestController
public class CalculateController {

    @GetMapping(path = "/api/v1/calculate/division", produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer getDivsion(@RequestParam int divisor, @RequestParam int dividend) {
        if (dividend == 0) {
            throw new IllegalArgumentException("dividend must not be zero.");
        }

        return new Answer(divisor / dividend, divisor % dividend);
    }

}

package aha.oremta.jp.controller;

import aha.oremta.jp.service.DoubleCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aha-oretama
 * @Date 2016/12/11
 */
@RestController
public class DoubleCalculateController {

    @Autowired
    private DoubleCalculateService doubleCalculateService;

    @GetMapping(path = "api/v1/double-calculate/division")
    public int getDoubleCalculateDivision(@RequestParam int divisor, @RequestParam int dividend){
        return doubleCalculateService.getDoubleDivision(divisor, dividend);
    }
}

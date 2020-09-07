package com.example.demo;

import com.example.demo.token.JwtToken;
import com.example.demo.token.JwtTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private JwtToken tokenService;
    @GetMapping("/hello")
    public String sayHello() {
        logger.info("hello");
//        logger.info();
//        logger.debug();
        return "hello";
    }

    @GetMapping("/token")
    public String returnJwtToken(){
        return tokenService.createClientToken();
    }

    @PostMapping("/token/decode")
    public JwtTokenResponse returnDecodedJwtToken(@RequestHeader("JWT") String jwt){
        return tokenService.decodeToken(jwt);
    }

}
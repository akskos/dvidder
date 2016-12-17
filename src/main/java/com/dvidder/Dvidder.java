package com.dvidder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Dvidder {

        @RequestMapping("/")
        @ResponseBody
        public String index() {
            return "Share dveeds with your friends :)";
        }
        
        @RequestMapping("/login")
        public String login(@RequestParam(required=false) String logout) {
            if (logout != null) {
                SecurityContextHolder.clearContext();
            }
            return "login";
        }
    
	public static void main(String[] args) {
		SpringApplication.run(Dvidder.class, args);
	}
}

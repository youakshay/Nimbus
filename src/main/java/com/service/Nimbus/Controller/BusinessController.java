package com.service.Nimbus.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {
    @PostMapping("/content")
    public void content(@RequestBody String content) {
        // Logic to handle content creation or management
        System.out.println("Handling content: " + content);
        // You can add your content handling logic here
    }
}

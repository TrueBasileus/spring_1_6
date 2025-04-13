package com.example.spring_1_6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class Spring16ApplicationTests {

    @Test
    void contextLoads() {
    }

}

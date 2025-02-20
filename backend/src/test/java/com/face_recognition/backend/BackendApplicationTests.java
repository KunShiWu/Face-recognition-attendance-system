package com.face_recognition.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        Set<Integer> uniqueNumbers = ThreadLocalRandom.current()
                .ints(100000, 1000000) // 指定范围 [100000, 999999]
                .distinct()
                .limit(1)
                .boxed()
                .collect(Collectors.toSet());
        System.out.println("Generated numbers: " + uniqueNumbers.hashCode());
    }

}

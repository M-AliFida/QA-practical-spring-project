package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.UserRepository;
import com.app.SuperMarketSystem.service.UserService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)

@SpringBootTest
@Transactional
class SuperMarketSystemApplicationTests {


    @Test
    void contextLoads() {
    }


}
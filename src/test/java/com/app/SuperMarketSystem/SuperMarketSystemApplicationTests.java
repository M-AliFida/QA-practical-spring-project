package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.UserRepository;
import com.app.SuperMarketSystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class SuperMarketSystemApplicationTests {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number")).collect(Collectors.toList()));
        assertEquals(1, userService.findAllUsers().size());
    }
    @Test
    public void getUsersById(){
        Integer id = 1;
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.getById(id)).thenReturn(user);
        assertEquals(user, userService.getUserById(id));
    }

}
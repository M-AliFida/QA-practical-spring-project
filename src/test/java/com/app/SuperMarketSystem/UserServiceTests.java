package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.Order;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.UserRepository;
import com.app.SuperMarketSystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTests {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number")).collect(Collectors.toList()));
        assertNotNull(userService.findAllUsers().getData());
    }

    @Test
    public void getUsersById() {
        Integer id = 1;
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.getById(id)).thenReturn(user);
        assertEquals(user, userService.getUserById(id).getData());
    }

    @Test
    public void saveUserTest() {
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addNewUser(user).getData());
    }

    @Test
    public void deleteUserByIdTest1() {
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.getById(user.getId())).thenReturn(user);
        assertEquals(200, userService.deleteUserById(user.getId()).getStatus());
    }

    @Test
    public void deleteUserTest2() {
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        assertEquals(200, userService.deleteUser(user).getStatus());
    }

    @Test
    public void deleteUserByTest3() {
        User user = new User(2, "Test2", "User2", "test2@gmail.com", 20, "password2", "address2", "phone number2");
        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }


    @Test
    public void updateUserTest() {
        User oldUser = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");

        User updatedUser = new User(1, "TestUpdated", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.getById(updatedUser.getId())).thenReturn(oldUser);
        assertEquals(200, userService.updateUser(updatedUser).getStatus());
        assertEquals(updatedUser, userService.updateUser(updatedUser).getData());
    }

    @Test
    public void getUserOrdersTest() {
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        Product product = new Product("Product Id", "Product name", 1000.0);
        Order order = new Order("Order Id", 10000.0, LocalDateTime.now(), "pending");
        order.getProducts().add(product);
        user.getOrders().add(order);

        when(userRepository.getById(user.getId())).thenReturn(user);
        assertEquals(user.getOrders(), userService.getOrdersByUserId(user.getId()).getData());
    }
}
package com.app.SuperMarketSystem.service;

import com.app.SuperMarketSystem.dto.ApiResponse;
import com.app.SuperMarketSystem.model.Order;
import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse findAllUsers() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<User> userList = userRepository.findAll();
            if (userList.isEmpty()) {
                apiResponse.setMessage("No users found within the database");
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setData(null);
            } else {
                apiResponse.setMessage("Successfully fetched users from within database");
                apiResponse.setData(userList);
                apiResponse.setStatus(HttpStatus.OK.value());
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse addNewUser(User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            userRepository.save(user);
            apiResponse.setMessage("Successfully added user within the database");
            apiResponse.setData(user);
            apiResponse.setStatus(HttpStatus.OK.value());
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse deleteUserById(Integer userId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userRepository.getById(userId);
            if (null != user) {
                user.setOrders(null);
                userRepository.delete(user);
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successfully deleted user from within the database");
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such user found against this ID");
            }
            apiResponse.setData(null);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse deleteUser(User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            userRepository.delete(user);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Successfully deleted user from within the database");
            apiResponse.setData(null);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse updateUser(User updatedUser) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User existingUser = userRepository.getById(updatedUser.getId());
            if (null != existingUser) {
                userRepository.save(updatedUser);
                apiResponse.setMessage("Successfully updated user within the database");
                apiResponse.setData(updatedUser);
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such user found against this ID");
                apiResponse.setData(null);
            }
            return apiResponse;

        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse getUserById(Integer userId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userRepository.getById(userId);
            if (null != user) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successful");
                apiResponse.setData(user);
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such user found within the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse getOrdersByUserId(Integer userId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userRepository.getById(userId);
            if (null != user) {
                List<Order> orders = user.getOrders();
                if (orders.isEmpty()) {
                    apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    apiResponse.setMessage("No orders made yet by this user");
                } else {
                    apiResponse.setStatus(HttpStatus.OK.value());
                    apiResponse.setMessage("These are the orders made by the user");
                }
                apiResponse.setData(orders);
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such user found against this ID within the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }
}
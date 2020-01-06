package com.Lalatendu.User.userservice.controller;

import com.Lalatendu.User.userservice.bean.UserRequest;
import com.Lalatendu.User.userservice.bean.UserResponse;
import com.Lalatendu.User.userservice.bean.UserUpdateRequest;
import com.Lalatendu.User.userservice.entity.User;
import com.Lalatendu.User.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "v1/addUser")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userBody) {
        UserResponse newUser = userService.addUser(userBody);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "v1/updateByUserId")
    public ResponseEntity<UserResponse> updateUserById(@RequestBody UserUpdateRequest userUpdateRequestRequest) {
        UserResponse updatedUser = userService.updateUserById(userUpdateRequestRequest);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @PutMapping(path = "v1/updateByUserEmail")
    public ResponseEntity<UserResponse> updateUserByEmail(@RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUserByEmail(userRequest);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @GetMapping(path = "v1/Users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping(path = "v1/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        UserResponse deleteUser = userService.deleteUser(id);
        return new ResponseEntity(deleteUser, HttpStatus.OK);
    }

    @GetMapping(path = "v1/usersBirthDateInCurrentMonth")
    public List<User> getAllUsersBirthDateInCurrentMonth() {
        List<User> users = userService.getAllUsersBirthDateInCurrentMonth();
        return users;
    }

    @GetMapping(path = "v1/countBirthDateMonthWise")
    public HashMap<Month, Integer> countBirthDateMonthWise() {
        HashMap<Month, Integer> birthDateMonthWise = userService.countBirthDateMonthWise();
        return birthDateMonthWise;
    }
}

package com.duycs.user.controller;

import com.duycs.user.VO.ResponseTemplateVO;
import com.duycs.user.entity.User;
import com.duycs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User addUser(@RequestBody AddUserVM addUserVM) {
        return userService.addUser(addUserVM);
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserVM updateUserVM) {
        return userService.updateUser(updateUserVM);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }


}

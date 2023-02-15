package com.kameleoon.task.controller;

import com.kameleoon.task.entity.User;
import com.kameleoon.task.payload.UserDto;
import com.kameleoon.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> saveUSer(@RequestBody UserDto userDto){
         User user = userService.saveUSer(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }


}

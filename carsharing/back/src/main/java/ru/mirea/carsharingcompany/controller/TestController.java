package ru.mirea.carsharingcompany.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.carsharingcompany.domain.User;
import ru.mirea.carsharingcompany.repo.UserRepo;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class TestController {


    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<User> getUser(){
        return this.userRepo.findAll();
    }
}

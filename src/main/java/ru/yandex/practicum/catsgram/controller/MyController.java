package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping
public class MyController {
    // Добавьте метод сюда
    @GetMapping("/card")
    ResponseEntity<String> getUser(@RequestParam("name") String userName){


        return ResponseEntity.status(HttpStatus.OK).body(userName + " - "  + (int) (Math.random() * 1000));
    }
}
package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(name="page", defaultValue = "0", required = false) Integer page,
                              @RequestParam(name="size", defaultValue = "10", required = false) Integer size,
                              @RequestParam(name="sort", defaultValue = "desc", required = false) String sort) {
        if(!(sort.equals("asc") || sort.equals("desc")) || (page < 0 || size <= 0))
            throw new IllegalArgumentException();
        int from = page * size;
        return postService.findAll(size, from, sort);
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        return postService.findById(postId);
    }
}
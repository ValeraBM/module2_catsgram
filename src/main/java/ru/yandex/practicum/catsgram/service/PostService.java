package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static int countPost=0;
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(int size, int from, String sort) {

       List<Post> result = posts
                .stream()
                .sorted((p1, p2) -> { int comp = p1.getCreationDate().compareTo(p2.getCreationDate());
                    if(sort.equalsIgnoreCase("desc")) {
                        comp = -1 * comp;
                    }
                    return comp;} )
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());

       return result;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
        post.setId(countPost++);
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }
}

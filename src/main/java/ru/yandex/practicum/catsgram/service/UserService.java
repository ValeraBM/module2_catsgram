package ru.yandex.practicum.catsgram.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private final HashMap<String, User> users = new HashMap<>();


    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User create(User user) {
        if (Strings.isBlank(user.getEmail())) {
            throw new InvalidEmailException("Пустой email! ");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь уже есть - " + user.getEmail());
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User update(User user) {
        if (Strings.isBlank(user.getEmail())) {
            throw new InvalidEmailException("Пустой email! ");
        }
        users.put(user.getEmail(), user);
        return user;
    }
    public User findUserByEmail(String email){
        if(email == null){
            return null;
        }
        return users.get(email);
    }

    public List<User> findByNickname(String nickname){
        List<User> result = new ArrayList<>();
        for(User user : users.values()){
            if (user.getNickname().equals(nickname))
                result.add(user);
        }
        return result;
    }
}

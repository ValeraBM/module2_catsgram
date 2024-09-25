package ru.yandex.practicum.catsgram.model;

import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;

    @Override
    public int hashCode(){
        return email.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null || Strings.isEmpty(email)) return false;
        User u = (User) o;
        return email.equals(u.email);
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
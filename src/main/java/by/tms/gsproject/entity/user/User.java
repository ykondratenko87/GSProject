package by.tms.gsproject.entity.user;

import by.tms.gsproject.entity.user.UserRole.Role;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;

    public User(long id, String name, String surname, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public User(Long id, String name, String surname, String login, String password, String role) {
    }
}
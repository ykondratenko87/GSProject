package by.tms.gsproject.api.user;

import by.tms.gsproject.entity.user.UserRole.Role;
import lombok.Data;

@Data
public class UserRequest {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;
}
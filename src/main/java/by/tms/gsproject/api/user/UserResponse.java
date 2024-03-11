package by.tms.gsproject.api.user;

import by.tms.gsproject.entity.user.UserRole.Role;
import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
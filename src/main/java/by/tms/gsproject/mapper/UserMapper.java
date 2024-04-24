package by.tms.gsproject.mapper;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.api.user.UserResponse;
import by.tms.gsproject.entity.user.User;

public class UserMapper {
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setLogin(user.getLogin());
//        userResponse.setPassword(user.getPassword()); //для вывода пароля на экран
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
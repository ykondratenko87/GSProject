package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.api.user.UserResponse;
import by.tms.gsproject.entity.user.User;

import java.util.Collection;

public interface UserServiceInterface {
    void register(UserRequest userRequest);

    User authenticate(UserRequest userRequest);

    void deleteUserById(Long userId);

    UserResponse getUserByLogin(String userLogin);

    UserResponse getUserById(long userId);

    Collection<User> allUsers();
}
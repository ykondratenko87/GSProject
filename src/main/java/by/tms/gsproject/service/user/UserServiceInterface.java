package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;

public interface UserServiceInterface {
    void register(UserRequest userRequest);
    User authenticate (UserRequest userRequest);

    void deleteUserById(Long userId);

    void updateUser(UserRequest userRequest);
}
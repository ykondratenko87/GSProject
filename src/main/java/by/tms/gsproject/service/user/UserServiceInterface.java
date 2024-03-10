package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;

public interface UserServiceInterface {
    void register(UserRequest userRequest);

    void deleteUserById(Long userId);

    void updateUser(UserRequest userRequest);
}
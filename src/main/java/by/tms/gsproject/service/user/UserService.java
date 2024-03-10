package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.mapper.UserMapper;
import by.tms.gsproject.repository.UserFileRepository;
import by.tms.gsproject.repository.UserRepository;

public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserFileRepository();
    }

    @Override
    public void register(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        userRepository.add(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        userRepository.update(user);
    }
}
package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.mapper.UserMapper;
import by.tms.gsproject.repository.UserFileRepository;
import by.tms.gsproject.repository.UserRepository;

import java.util.Collection;

public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserFileRepository();
    }

    @Override
    public void register(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);

        Collection<User> allUsers = userRepository.allUsers();
        boolean userExists = allUsers.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));
        if (userExists) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }
        userRepository.add(user);
    }

    @Override
    public User authenticate(UserRequest userRequest) {
        Collection<User> allUsers = userRepository.allUsers();
        User authenticatedUser = null;
        for (User user : allUsers) {
            if (user.getLogin().equals(userRequest.getLogin()) && user.getPassword().equals(userRequest.getPassword())) {
                authenticatedUser = user;
                break;
            }
        }
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Пользователь с таким логином и паролем не найден");
        }
        return authenticatedUser;
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
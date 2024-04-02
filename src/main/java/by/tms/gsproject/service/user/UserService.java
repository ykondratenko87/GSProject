package by.tms.gsproject.service.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.api.user.UserResponse;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.mapper.UserMapper;
import by.tms.gsproject.repository.user.UserFileRepository;
import by.tms.gsproject.repository.user.UserJDBCRepository;
import by.tms.gsproject.repository.user.UserRepository;

import java.util.Collection;

public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserJDBCRepository();
    }

    @Override
    public void register(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        Collection<User> allUsers = userRepository.allUsers();
        boolean userExists = allUsers.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));
        if (userExists) {
            throw new IllegalArgumentException("Пользователь уже существует");
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
        return authenticatedUser;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getUserByLogin(String userLogin) {
        UserMapper userMapper = new UserMapper();
        User user = userRepository.findByLogin(userLogin);
        if (user != null) {
            return userMapper.toResponse(user);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUserById(long userId) {
        UserMapper userMapper = new UserMapper();
        User user = userRepository.getUserById(userId);
        if (user != null) {
            return userMapper.toResponse(user);
        } else {
            return null;
        }
    }

    @Override
    public Collection<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public void updateUserDates(UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        userRepository.update(user);
    }
}
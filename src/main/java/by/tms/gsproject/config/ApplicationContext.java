package by.tms.gsproject.config;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.mapper.UserMapper;
import by.tms.gsproject.repository.user.UserJDBCRepository;
import by.tms.gsproject.service.user.UserService;
import lombok.Getter;

@Getter
public class ApplicationContext {
    private static ApplicationContext applicationContext;
    private final UserJDBCRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    private ApplicationContext() {
        userMapper = new UserMapper();
        userRepository = new UserJDBCRepository();
        userService = new UserService();

    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public UserRequest createUserRequest() {
        return new UserRequest();
    }
}
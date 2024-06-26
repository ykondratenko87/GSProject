package by.tms.gsproject.repository.user;

import by.tms.gsproject.entity.user.User;

import java.util.Collection;

public interface UserRepository {
    User add(User user);

    void deleteById(Long userId);

    Collection<User> allUsers();

    User getUserById(Long userId);

    User findByLogin(String userLogin);

    void update(User user);
}
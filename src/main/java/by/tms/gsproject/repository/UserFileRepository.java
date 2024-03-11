package by.tms.gsproject.repository;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserFileRepository implements UserRepository {
    private static final String FILE_PATH = "d:\\Java\\C72-JavaProjects\\GSProject\\src\\main\\resources\\files\\users.ser";

    @Override
    public User add(User user) {
        long lastId = getLastId();
        lastId++;
        user.setId(lastId);
        Collection<User> allUsers = allUsers();
        if (allUsers.isEmpty()) {
            user.setRole(UserRole.Role.ADMIN);
        } else {
            user.setRole(UserRole.Role.CLIENT);
        }
        allUsers.add(user);
        saveUsers(allUsers);
        return user;
    }

    @Override
    public void deleteById(Long userId) {
        Collection<User> allUsers = allUsers();
        allUsers.removeIf(user -> Long.valueOf(user.getId()).equals(userId));
        saveUsers(allUsers);
    }

    @Override
    public User update(User user) {
        Collection<User> allUsers = allUsers();
        allUsers.removeIf(u -> Long.valueOf(u.getId()).equals(user.getId()));
        allUsers.add(user);
        saveUsers(allUsers);
        return user;
    }

    @Override
    public Collection<User> allUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Collection<User> users = (Collection<User>) ois.readObject();
            return users != null ? users : new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public User getUserById(Long userId) {
        return allUsers().stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }
    private long getLastId() {
        Collection<User> users = allUsers();
        return users.stream().mapToLong(User::getId).max().orElse(0);
    }

    private void saveUsers(Collection<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
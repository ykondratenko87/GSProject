package by.tms.gsproject;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.repository.UserFileRepository;
import by.tms.gsproject.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserFileRepository();

        System.out.println("Все пользователи");
        for (User user : userRepository.allUsers()) {
            System.out.println(user);
        }
    }
}
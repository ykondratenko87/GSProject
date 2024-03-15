package by.tms.gsproject;

import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.repository.product.ProductFileRepository;
import by.tms.gsproject.repository.product.ProductJDBCRepository;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.user.UserFileRepository;
import by.tms.gsproject.repository.user.UserJDBCRepository;
import by.tms.gsproject.repository.user.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserJDBCRepository();
        ProductRepository productRepository = new ProductJDBCRepository();
        System.out.println("Все пользователи");
        for (User user : userRepository.allUsers()) {
            System.out.println(user);
        }
        System.out.println("Все товары");
        for (Product product : productRepository.allProducts()) {
            System.out.println(product);
        }
    }
}
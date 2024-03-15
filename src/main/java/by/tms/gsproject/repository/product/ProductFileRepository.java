package by.tms.gsproject.repository.product;

import by.tms.gsproject.entity.product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProductFileRepository implements ProductRepository, Serializable {
    private static final String FILE_PATH = "d:\\Java\\C72-JavaProjects\\GSProject\\src\\main\\resources\\files\\product.ser";

    @Override
    public void add(Product product) {
        Collection<Product> allProducts = allProducts();
        boolean productExists = false;
        for (Product existingProduct : allProducts) {
            if (existingProduct.getName().equals(product.getName()) && existingProduct.getType().equals(product.getType()) && existingProduct.getPrice() == product.getPrice()) {
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            long lastId = getLastId();
            lastId++;
            product.setId(lastId);
            allProducts.add(product);
        }
        saveProducts(allProducts);
    }

    @Override
    public void deleteById(long productId) {
        Collection<Product> allProducts = allProducts();
        allProducts.removeIf(product -> product.getId() == productId);
        saveProducts(allProducts);
    }

    @Override
    public Product findById(long productId) {
        return allProducts().stream().filter(product -> product.getId() == productId).findFirst().orElse(null);
    }

    @Override
    public Collection<Product> allProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Collection<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveProducts(Collection<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getLastId() {
        Collection<Product> products = allProducts();
        return products.stream().mapToLong(Product::getId).max().orElse(0);
    }
}
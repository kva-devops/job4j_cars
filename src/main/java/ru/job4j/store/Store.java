package ru.job4j.store;

import ru.job4j.model.*;

import java.util.List;

public interface Store {
    List<Item> findAllItems();

    User findUserByEmail(String email);

    void saveUser(User user);

    List<CategoryCar> findAllCategories();

    List<Body> findAllBodies();

    List<Brand> findAllBrands();

    void saveItem(Item item, String[] categoryId, String[] bodyId, String[] brandId);

    List<Item> findItemsForUser(int id);

    void deleteItem(Integer id);

    List<Item> findItemsByLastDay();

    List<Item> findItemsWithPhoto();

    List<Item> findItemsByBrand(String brandName);

    void updatePhotoStatus(int userId, int itemId);

    void checkSaleItem(int userId);
}

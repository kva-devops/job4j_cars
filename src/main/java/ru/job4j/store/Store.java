package ru.job4j.store;

import ru.job4j.model.Body;
import ru.job4j.model.Brand;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public interface Store {
    List<Item> findAllItems();

    User findUserByEmail(String email);

    void saveUser(User user);

    List<Body> findAllBodies();

    List<Brand> findAllBrands();

    void saveItem(Item item, String[] bodyId, String[] brandId);

    List<Item> findItemsForUser(int id);

    void deleteItem(Integer id);

    List<Item> findItemsByLastDay();

    List<Item> findItemsWithPhoto();

    List<Item> findItemsByBrand(int brandId);

    void updatePhotoStatus(int userId, int itemId);
}

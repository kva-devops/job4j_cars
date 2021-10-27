package ru.job4j;

import org.junit.Test;
import ru.job4j.model.*;
import ru.job4j.store.HbnStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HbrStoreTest {

    @Test
    public void whenSaveOneUserAndAddOneItemsAndFindAllItems() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item = new Item("desc", false, user);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item, categoryId, bodyId, brandId);
        List<Item> allItems = new ArrayList<>(store.findAllItems());
        assertThat(allItems.get(0).getDescription(), is("desc"));
        assertThat(allItems.get(0).getUser(), is(user));
    }

    @Test
    public void whenSaveTwoUserAndAddTwoItemsAndFindItemsForUser() {
        HbnStore store = new HbnStore();
        User user1 = new User("user1", "test@test.ru", "1111");
        User user2 = new User("user2", "test2@test.ru", "1111");
        store.saveUser(user1);
        store.saveUser(user2);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item1 = new Item("item1", false, user1);
        Item item2 = new Item("item2", false, user2);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item1, categoryId, bodyId, brandId);
        store.saveItem(item2, categoryId, bodyId, brandId);
        List<Item> itemListForUser1 = new ArrayList<>(store.findItemsForUser(1));
        assertThat(itemListForUser1.get(0).getDescription(), is("item1"));
        assertThat(itemListForUser1.get(0).getUser(), is(user1));
        assertThat(itemListForUser1.size(), is(1));
    }

    @Test
    public void whenAddOneUserAndFindUserByEmail() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        User resultUser = store.findUserByEmail("test@test.ru");
        assertThat(resultUser, is(user));
    }

    @Test
    public void whenAddOneCategoriesAndFindAllCategories() {
        HbnStore store = new HbnStore();
        CategoryCar category = new CategoryCar("category");
        store.saveCategory(category);
        assertThat(store.findAllCategories().get(0), is(category));
    }

    @Test
    public void whenAddOneBodyAndFindAllBodies() {
        HbnStore store = new HbnStore();
        Body body = new Body("body");
        store.saveBody(body);
        assertThat(store.findAllBodies().get(0), is(body));
    }

    @Test
    public void whenAddOneBrandAndFindAllBrands() {
        HbnStore store = new HbnStore();
        Brand brand = new Brand("brand");
        store.saveBrand(brand);
        assertThat(store.findAllBrands().get(0), is(brand));
    }

    @Test
    public void whenAddOneUserAndAddTwoItemsAndDeleteOneItem() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item = new Item("item", false, user);
        Item itemForDelete = new Item("delete item", false, user);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item, categoryId, bodyId, brandId);
        store.saveItem(itemForDelete, categoryId, bodyId, brandId);
        store.deleteItem(2);
        List<Item> allItems = new ArrayList<>(store.findAllItems());
        assertThat(allItems.get(0).getDescription(), is("item"));
        assertThat(allItems.size(), is(1));
    }

    @Test
    public void whenAddTwoItemsWithDifferentTimeCreatedAndFindItemsLastDay() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item = new Item("item", false, user);
        Item itemOld = new Item("old item", false, user);
        itemOld.setCreated(new Date(10000));
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item, categoryId, bodyId, brandId);
        store.saveItem(itemOld, categoryId, bodyId, brandId);
        List<Item> lastDayItems = new ArrayList<>(store.findItemsByLastDay());
        assertThat(lastDayItems.get(0).getDescription(), is("item"));
        assertThat(lastDayItems.size(), is(1));
    }

    @Test
    public void whenAddOneUserAndAddTwoItemsFirstWithPhotoSecondWithoutPhotoAndFindItemsWithPhoto() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item itemWithPhoto = new Item("item with photo", false, user);
        Item itemWithoutPhoto = new Item("item without photo", false, user);
        itemWithPhoto.setPhoto(true);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(itemWithPhoto, categoryId, bodyId, brandId);
        store.saveItem(itemWithoutPhoto, categoryId, bodyId, brandId);
        List<Item> allItems = new ArrayList<>(store.findItemsWithPhoto());
        assertThat(allItems.get(0).getDescription(), is("item with photo"));
        assertThat(allItems.size(), is(1));
    }

    @Test
    public void whenAddOneUserAndAddTwoItemsWithDifferentBrandAndFindItemsByBrand() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand1 = new Brand("brand 1");
        Brand brand2 = new Brand("brand 2");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand1);
        store.saveBrand(brand2);
        store.saveBody(body);
        Item itemBrand1 = new Item("item brand 1", false, user);
        Item itemBrand2 = new Item("item brand 2", false, user);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId1 = new String[1];
        String[] brandId2 = new String[1];
        brandId1[0] = "1";
        brandId2[0] = "2";
        store.saveItem(itemBrand1, categoryId, bodyId, brandId1);
        store.saveItem(itemBrand2, categoryId, bodyId, brandId2);
        List<Item> itemListForBrand1 = new ArrayList<>(store.findItemsByBrand("brand 1"));
        assertThat(itemListForBrand1.get(0).getDescription(), is("item brand 1"));
        assertThat(itemListForBrand1.size(), is(1));
    }

    @Test
    public void whenAddOneUserAndAddOneItemAndUpdatePhotoStatus() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item = new Item("item", false, user);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item, categoryId, bodyId, brandId);
        store.updatePhotoStatus(1, 1);
        Item itemAfterUpdateStatusPhoto = store.findItemsForUser(1).get(0);
        assertThat(itemAfterUpdateStatusPhoto.isPhoto(), is(true));
    }

    @Test
    public void whenAddOneUserAndAddOneItemAndUpdateSaleStatusItem() {
        HbnStore store = new HbnStore();
        User user = new User("test", "test@test.ru", "1111");
        store.saveUser(user);
        CategoryCar category = new CategoryCar("category");
        Brand brand = new Brand("brand");
        Body body = new Body("body");
        store.saveCategory(category);
        store.saveBrand(brand);
        store.saveBody(body);
        Item item = new Item("item", false, user);
        String[] categoryId = new String[1];
        categoryId[0] = "1";
        String[] bodyId = new String[1];
        bodyId[0] = "1";
        String[] brandId = new String[1];
        brandId[0] = "1";
        store.saveItem(item, categoryId, bodyId, brandId);
        store.updateSaleStatus(1, 1);
        List<Item> allItem = store.findAllItems();
        assertThat(allItem.get(0).isSold(), is(true));
    }
}

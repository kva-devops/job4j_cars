package ru.job4j.store;

import ru.job4j.model.Item;

public class RunHbn {
    public static void main(String[] args) {
//        System.out.println("find Items with photo: id-");
//        for (Item item : HbnStore.instOf().findItemsWithPhoto()) {
//            System.out.println(item.getId());
//        }
        System.out.println("find Items some brand: ");
        for (Item item : HbnStore.instOf().findItemsByBrand("Toyota")) {
            System.out.println(item.getId());
        }
//        System.out.println("find Items last day: ");
//        for (Item item : HbnStore.instOf().findItemsByLastDay()) {
//            System.out.println(item.getId());
//        }

    }
}

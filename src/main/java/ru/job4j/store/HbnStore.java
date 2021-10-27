package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.*;

import java.util.List;
import java.util.function.Function;

public class HbnStore implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl =  command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public List<Item> findAllItems() {
        return this.tx(
                session -> session.createQuery(
                        "select i from ru.job4j.model.Item i "
                                + "left join fetch i.user "
                                + "left join fetch i.category "
                                + "left join fetch i.body "
                                + "left join fetch i.brand")
                        .list()
        );
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) this.tx(
                session -> session.createQuery("from ru.job4j.model.User where email=:email")
                            .setParameter("email", email).uniqueResult()
        );
    }

    @Override
    public void saveUser(User user) {
        this.tx(session -> session.save(user));
    }

    @Override
    public List<CategoryCar> findAllCategories() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.model.CategoryCar").list()
        );
    }

    @Override
    public List<Body> findAllBodies() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.model.Body").list()
        );
    }

    @Override
    public List<Brand> findAllBrands() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.model.Brand").list()
        );
    }

    @Override
    public void saveItem(Item item, String[] categoryId, String[] bodyId, String[] brandId) {
        this.tx(session -> {
            CategoryCar category = session.find(CategoryCar.class, Integer.parseInt(categoryId[0]));
            Body body = session.find(Body.class, Integer.parseInt(bodyId[0]));
            Brand brand = session.find(Brand.class, Integer.parseInt(brandId[0]));
            item.setCategory(category);
            item.setBody(body);
            item.setBrand(brand);
            session.save(item);
            return true;
        });
    }

    @Override
    public List<Item> findItemsForUser(int id) {
        return this.tx(
                session -> session.createQuery(
                        "select i from ru.job4j.model.Item i "
                                + "left join fetch i.user "
                                + "left join fetch i.category "
                                + "left join fetch i.body "
                                + "left join fetch i.brand "
                                + "where i.user.id = :id")
                        .setParameter("id", id)
                        .list()
        );
    }

    @Override
    public void deleteItem(Integer id) {
        this.tx(session -> {
            Item item = session.find(Item.class, id);
            session.delete(item);
            return true;
        });
    }

    @Override
    public List<Item> findItemsByLastDay() {
        return this.tx(session -> session.createQuery("from Item where day(current_timestamp - created) <= 1").list());
    }

    @Override
    public List<Item> findItemsWithPhoto() {
        return this.tx(session -> session.createQuery("from Item where photo = true").list());
    }

    @Override
    public List<Item> findItemsByBrand(String brandName) {
        return this.tx(session -> session.createQuery("from Item i where i.brand.name = :brandName")
                .setParameter("brandName", brandName).list());
    }

    @Override
    public void updatePhotoStatus(int userId, int itemId) {
        this.tx(session -> session.createQuery("update Item i set i.photo = true where i.user.id = :uId and i.id = :iId")
                .setParameter("uId", userId)
                .setParameter("iId", itemId)
                .executeUpdate());
    }

    @Override
    public void checkSaleItem(int itemId) {
        this.tx(session -> session.createQuery("update Item i set i.sold = true where i.id = :iId")
                .setParameter("iId", itemId)
                .executeUpdate()
        );
    }
}
package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.OrderDao;
import ua.com.project.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderRepository implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                "INSERT INTO `orders` (`date_created`, `payment`, `timeOfStay`) VALUES (?, ?, ?)")
                .setParameter(1, obj.getDate_created())
                .setParameter(2, obj.getPayment())
                .setParameter(3, obj.getTimeOfStay())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void update(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("update `orders` set date_created=?, payment=?, timeOfStay=? where id=?")
                .setParameter(1, obj.getDate_created())
                .setParameter(2, obj.getPayment())
                .setParameter(3, obj.getTimeOfStay())
                .setParameter(4, obj.getId())

                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void delete(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Order as o where o.id=:id")
                .setParameter("id", obj.getClient())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Order as o")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Order> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Order> order1 = entityManager.createNativeQuery("select * from `orders`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return order1;
    }

    @Override
    public Order findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Order order1 = entityManager.createQuery("select o from Order as o where  o.id=:id", Order.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return order1;
    }

    @Override
    public Order findByName(String payment) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Order order = entityManager.createQuery("select o from Order as o where  o.payment=:payment", Order.class)
                .setParameter("payment", payment)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return order;
    }
}

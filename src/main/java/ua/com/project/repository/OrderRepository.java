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
    public void saveNativeSQL(Order obj) {
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
    public void saveHQL(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateNativeSQL(Order obj) {
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
    public void updateHQL(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                "update Order set date_created=:date_cr, payment=:payment, timeOfStay=:time where id=:id")
                .setParameter("date_cr", obj.getDate_created())
                .setParameter("payment", obj.getPayment())
                .setParameter("time", obj.getTimeOfStay())
                .setParameter("id", obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void deleteNativeSQL(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from `orders` where id=?")
                .setParameter(1, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHQL(Order obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Order as o where o.id=:id")
                .setParameter("id", obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete * from `orders`")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Order")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Order> findAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Order> order1 = entityManager.createNativeQuery("select * from `orders`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return order1;
    }

    @Override
    public List<Order> findAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Order> order = entityManager.createQuery("select o from Order as o").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return order;
    }

    @Override
    public Order findByIdNativeSQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Order order = (Order) entityManager.createNativeQuery(
                "select * from `orders` where  id=?", Order.class)
                .setParameter(1, id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return order;
    }

    @Override
    public Order findByIdHQL(Long id) {
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
    public Order findByNameNativeSQL(String payment) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Order order = (Order) entityManager.createNativeQuery(
                "select * from `orders` where payment=?", Order.class)
                .setParameter(1, payment)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return order;
    }

    @Override
    public Order findByNameHQL(String payment) {
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

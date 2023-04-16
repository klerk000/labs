package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.RoomsHasOrderDao;
import ua.com.project.entity.Rooms;
import ua.com.project.entity.RoomsHasOrder;

import javax.persistence.EntityManager;
import java.util.List;

public class RoomsHasOrderRepository implements RoomsHasOrderDao {

    private final SessionFactory sessionFactory;

    public RoomsHasOrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveNativeSQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                        "INSERT INTO `rooms_has_order` (`name`) VALUES (?)")
                .setParameter(1, obj.getName())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveHQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateNativeSQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("update `rooms_has_order` set name=? where id=?")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateHQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("update RoomsHasOrder set name=:name where id=:id")
                .setParameter("name", obj.getName())
                .setParameter("id", obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteNativeSQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from `rooms_has_order` where id=?")
                .setParameter(1, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHQL(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from RoomsHasOrder where id=:id")
                .setParameter("id", obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete * from `rooms_has_order`")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from RoomsHasOrder as rhs")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<RoomsHasOrder> findAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<RoomsHasOrder> roomsHasOrders =
                entityManager.createNativeQuery("select * from `rooms_has_order`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrders;
    }

    @Override
    public List<RoomsHasOrder> findAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<RoomsHasOrder> roomsHasOrders =
                entityManager.createQuery("select rho from RoomsHasOrder as rho").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrders;
    }

    @Override
    public RoomsHasOrder findByIdNativeSQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder roomsHasOrder = (RoomsHasOrder) entityManager.createNativeQuery(
                "select * from `rooms_has_order` where id=?", RoomsHasOrder.class)
                .setParameter(1, id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrder;
    }

    @Override
    public RoomsHasOrder findByIdHQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder roomsHasOrder = entityManager.createQuery(
                        "select rho from RoomsHasOrder as rho where  rho.id=:id", RoomsHasOrder.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrder;
    }

    @Override
    public RoomsHasOrder findByNameNativeSQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder roomsHasOrder = (RoomsHasOrder) entityManager.createNativeQuery(
                "select * from `rooms_has_order` as rho where rho.name=?", RoomsHasOrder.class)
                .setParameter(1, name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrder;
    }

    @Override
    public RoomsHasOrder findByNameHQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder roomsHasOrder = entityManager.createQuery(
                        "select rho from RoomsHasOrder as rho where  rho.name=:name", RoomsHasOrder.class)
                .setParameter("name", name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return roomsHasOrder;
    }
}

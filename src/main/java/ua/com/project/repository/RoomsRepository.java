package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.RoomsDao;
import ua.com.project.entity.Order;
import ua.com.project.entity.Rooms;

import javax.persistence.EntityManager;
import java.util.List;

public class RoomsRepository implements RoomsDao {
    private final SessionFactory sessionFactory;

    public RoomsRepository(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public void saveNativeSQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                        "INSERT INTO `rooms` (`name`, `price`, `description`, `image`) VALUES (?, ?, ?, ?)")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getPrice())
                .setParameter(3, obj.getDescription())
                .setParameter(4, obj.getImage())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveHQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateNativeSQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("update `rooms` set name=?, price=?, description=?, image=? where id=?")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getPrice())
                .setParameter(3, obj.getDescription())
                .setParameter(4, obj.getImage())
                .setParameter(5, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateHQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("update Rooms set name=?1, price=?2, description=?3, image=?4 where id=?5")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getPrice())
                .setParameter(3, obj.getDescription())
                .setParameter(4, obj.getImage())
                .setParameter(5, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteNativeSQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from `rooms` as r where r.id=?")
                .setParameter(1, obj.getCategories())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHQL(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Rooms as r where r.id=:id")
                .setParameter("id", obj.getCategories())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from `rooms`")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Rooms as r")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Rooms> findAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Rooms> rooms = entityManager.createNativeQuery("select * from `rooms`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return rooms;
    }

    @Override
    public List<Rooms> findAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Rooms> rooms = entityManager.createQuery("select r from Rooms as r").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return rooms;
    }

    @Override
    public Rooms findByIdNativeSQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms room = (Rooms) entityManager.createNativeQuery(
                "select * from `rooms` as r where r.id=?", Rooms.class)
                .setParameter(1, id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return room;
    }

    @Override
    public Rooms findByIdHQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms room = entityManager.createQuery("select r from Rooms as r where  r.id=:id", Rooms.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return room;
    }

    @Override
    public Rooms findByNameNativeSQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms room = (Rooms) entityManager.createNativeQuery(
                "select * from `rooms` as r where  r.name=?", Rooms.class)
                .setParameter(1, name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return room;
    }

    @Override
    public Rooms findByNameHQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms room = entityManager.createQuery("select r from Rooms as r where  r.name=:name", Rooms.class)
                .setParameter("name", name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return room;
    }
}
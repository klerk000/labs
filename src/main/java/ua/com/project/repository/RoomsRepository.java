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
    public void save(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // SQL
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
    public void update(Rooms obj) {
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
    public void delete(Rooms obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Rooms as r where r.id=:id")
                .setParameter("id", obj.getCategories())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Rooms as r")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Rooms> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Rooms> rooms1 = entityManager.createNativeQuery("select * from `rooms`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return rooms1;
    }

    @Override
    public Rooms findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms rooms1 = entityManager.createQuery("select r from Rooms as r where  r.id=:id", Rooms.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return rooms1;
    }

    @Override
    public Rooms findByName(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Rooms rooms1 = entityManager.createQuery("select r from Rooms as r where  r.name=:name", Rooms.class)
                .setParameter("name", name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return rooms1;
    }
}
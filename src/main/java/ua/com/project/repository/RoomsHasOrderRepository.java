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
    public void save(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // SQL
        entityManager.createNativeQuery(
                        "INSERT INTO `rooms_has_order` (`name`) VALUES (?)")
                .setParameter(1, obj.getName())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void update(RoomsHasOrder obj) {
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
    public void delete(RoomsHasOrder obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from RoomsHasOrder as rhs where rhs.id=:id")
                .setParameter("id", obj.getRoomsList())
                .setParameter("id", obj.getOrder())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from RoomsHasOrder as rhs")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<RoomsHasOrder> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<RoomsHasOrder> rhs1 = entityManager.createNativeQuery("select * from `rooms_has_order`")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return rhs1;
    }

    @Override
    public RoomsHasOrder findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder rhs1 = entityManager.createQuery(
                "select rhs from RoomsHasOrder as rhs where  rhs.id=:id", RoomsHasOrder.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return rhs1;
    }

    @Override
    public RoomsHasOrder findByName(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        RoomsHasOrder rhs1 = entityManager.createQuery(
                "select rhs from RoomsHasOrder as rhs where  rhs.name=:name", RoomsHasOrder.class)
                .setParameter("name", name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return rhs1;
    }
}

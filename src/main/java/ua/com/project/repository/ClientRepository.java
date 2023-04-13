package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.ClientDao;
import ua.com.project.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientRepository implements ClientDao {
    private final SessionFactory sessionFactory;

    public ClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // SQL
        entityManager.createNativeQuery("INSERT INTO `client` (`surname`, `lastname`, `age`, `phone` , `email`) VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, obj.getSurname())
                .setParameter(2, obj.getLastname())
                .setParameter(3, obj.getAge())
                .setParameter(4, obj.getPhone())
                .setParameter(5, obj.getEmail())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void update(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("update `client` set surname=?, lastname=?, age=?, phone=?,  email=? where id=?")
                .setParameter(1, obj.getSurname())
                .setParameter(2, obj.getLastname())
                .setParameter(3, obj.getAge())
                .setParameter(4, obj.getPhone())
                .setParameter(5, obj.getEmail())
                .setParameter(6, obj.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void delete(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Client as p where p.id=:id")
                .setParameter("id", obj.getOrder())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Client as c")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Client> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Client> clients1 = entityManager.createNativeQuery("select * from `client`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients1;
    }

    @Override
    public Client findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client clients1 = entityManager.createQuery("select p from Client as p where  p.id=:id", Client.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients1;
    }

    @Override
    public Client findByName(String lastname) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client clients = entityManager.createQuery("select p from Client as p where  p.lastname=:lastname", Client.class)
                .setParameter("lastname", lastname)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients;
    }
}

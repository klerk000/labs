package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.ClientDao;
import ua.com.project.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientRepository implements ClientDao {
    private final SessionFactory sessionFactory;

    public ClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveNativeSQL(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

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
    public void saveHQL(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateNativeSQL(Client obj) {
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
    public void updateHQL(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                "update Client set surname=:surname, lastname=:lastname, age=:age, " +
                        "phone=:phone, email=:email where id=:id")
                .setParameter("surname", obj.getSurname())
                .setParameter("lastname", obj.getLastname())
                .setParameter("age", obj.getAge())
                .setParameter("phone", obj.getPhone())
                .setParameter("email", obj.getEmail())
                .setParameter("id", obj.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void deleteNativeSQL(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from `client` where id=?")
                .setParameter(1, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHQL(Client obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Client where id=:id")
                .setParameter("id", obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete * from `client`")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Client")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Client> findAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Client> clients = entityManager.createNativeQuery("select * from `client`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients;
    }

    @Override
    public List<Client> findAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Client> clients = entityManager.createQuery("SELECT c FROM Client c").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return clients;
    }

    @Override
    public Client findByIdNativeSQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client = (Client) entityManager.createNativeQuery(
                "select * from `client` where id=?", Client.class)
                .setParameter(1, id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return client;
    }

    @Override
    public Client findByIdHQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client = (Client) entityManager.createQuery(
                "select c from Client as c where  c.id=:id")
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return client;
    }

    @Override
    public Client findByNameNativeSQL(String lastname) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client = (Client) entityManager.createNativeQuery(
                "select * from `client` as c where c.lastname=?", Client.class)
                .setParameter(1, lastname)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return client;
    }

    @Override
    public Client findByNameHQL(String lastname) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client = (Client) entityManager.createQuery(
                "select c from Client as c where  c.lastname=:lastname")
                .setParameter("lastname", lastname)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return client;
    }
}

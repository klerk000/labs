package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.CategoryDao;
import ua.com.project.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository implements CategoryDao{
    private final SessionFactory sessionFactory;

    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveNativeSQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                        "SELECT * FROM `category` WHERE `name` = ?1", Category.class)
                .setParameter(1, obj.getName());
        List<Category> categories = query.getResultList();
        if (!categories.isEmpty()) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new RuntimeException("Така категорія вже є");
        }

        entityManager.createNativeQuery(
                        "INSERT INTO `category` (`name`, `description`, `image`) VALUES (?, ?, ?)")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getDescription())
                .setParameter(3, obj.getImage())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void saveHQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                        "SELECT c FROM Category as c WHERE c.name = :name", Category.class)
                .setParameter("name", obj.getName());
        List<Category> categories = query.getResultList();
        if (!categories.isEmpty()) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new RuntimeException("Категория с таким названием уже существует");
        }
        entityManager.persist(obj);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateNativeSQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                "update `category` set name=?, description=?, image=? where id=?")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getDescription())
                .setParameter(3, obj.getImage())
                .setParameter(4, obj.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void updateHQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "update Category set name=:name, description=:description, image=:image where id=:id")
                .setParameter("name", obj.getName())
                .setParameter("description", obj.getDescription())
                .setParameter("image", obj.getImage())
                .setParameter("id", obj.getId());

        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteNativeSQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                "delete from `category` where id=?")
                .setParameter(1, obj.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHQL(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "delete from Category where id=:id")
                .setParameter("id", obj.getId());

        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public void deleteAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery(
                "delete from `category`")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                "delete from Category")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Category> findAllNativeSQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Category> category1 = entityManager.createNativeQuery(
                "select * from `category`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public List<Category> findAllHQL() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Category> query = entityManager.createQuery(
                "SELECT c FROM Category as c", Category.class);

        List<Category> categories =  query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return categories;
    }

    @Override
    public Category findByIdNativeSQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM `category` WHERE id = :id", Category.class)
                .setParameter("id", id);

        Category category1 = (Category) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public Category findByIdHQL(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "SELECT c FROM Category as c WHERE c.id=:id")
                .setParameter("id", id);

        Category category1 = (Category) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public Category findByNameNativeSQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                        "SELECT * FROM `category` WHERE name = :name", Category.class)
                .setParameter("name", name);

        Category category1 = (Category) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public Category findByNameHQL(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category1 = (Category) entityManager.createQuery(
                "SELECT c FROM Category as c WHERE c.name = :name")
                .setParameter("name", name)
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }
}
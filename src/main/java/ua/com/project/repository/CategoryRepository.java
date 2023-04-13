package ua.com.project.repository;

import org.hibernate.SessionFactory;
import ua.com.project.dao.CategoryDao;
import ua.com.project.entity.Category;
import ua.com.project.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryRepository implements CategoryDao{
    private final SessionFactory sessionFactory;

    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // SQL
        entityManager.createNativeQuery("INSERT INTO `category` (`name`, `description`, `image`) VALUES (?, ?, ?)")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getDescription())
                .setParameter(3, obj.getImage())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void update(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("update `category` set name=?, description=?, image=? where id=?")
                .setParameter(1, obj.getName())
                .setParameter(2, obj.getDescription())
                .setParameter(3, obj.getImage())
                .setParameter(4, obj.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void delete(Category obj) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Category as c where c.id=:id")
                .setParameter("id", obj.getRooms())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Category as c")
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Category> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Category> category1 = entityManager.createNativeQuery("select * from `category`").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public Category findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category1 = entityManager.createQuery("select p from Category as p where  p.id=:id", Category.class)
                .setParameter("id", id)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }

    @Override
    public Category findByName(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category1 = entityManager.createQuery("select c from Category as c where  c.name=:name",
                        Category.class)
                .setParameter("name", name)
                .getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return category1;
    }
}
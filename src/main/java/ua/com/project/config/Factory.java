package ua.com.project.config;

import org.hibernate.SessionFactory;
import ua.com.project.dao.*;
import ua.com.project.repository.*;

import javax.persistence.Persistence;

public class Factory {
    public final static Factory INSTANCE = new Factory();
    public static Factory getInstance() {
        return INSTANCE;
    }

    private final SessionFactory session;
    public Factory() {
        this.session = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }
    public CategoryDao getCategoryDao(){ return new CategoryRepository(session); }
    public ClientDao getClientDao() { return new ClientRepository(session); }
    public OrderDao getOrderDao(){ return new OrderRepository(session); }
    public RoomsHasOrderDao getRoomsHasOrderDao(){ return new RoomsHasOrderRepository(session); }
    public RoomsDao getRoomsDao(){ return new RoomsRepository(session); }
}

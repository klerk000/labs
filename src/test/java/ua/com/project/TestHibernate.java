package ua.com.project;

import org.junit.Test;
import ua.com.project.config.Factory;
import ua.com.project.dao.*;
import ua.com.project.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHibernate {

    private final Factory factory = Factory.getInstance();

    @Test
    public void Test1(){

        //Test Category
        CategoryDao categoryDao = factory.getCategoryDao();

        Category category = new Category();

        category.setName("Lux");
        category.setDescription("Over price rooms");
        category.setImage("/images/lux.jpg");

        // save
        categoryDao.save(category);
        // findById
        Category category1 = categoryDao.findById(1L);

        // [1, 1] - true / [1,0] -

        assertEquals(1L, category1.getId());

        // findByName
        Category category2 = categoryDao.findByName("Lux");
        assertEquals(1L, category1.getId());

        // findAll
        List<Category> categoryList = categoryDao.findAll();
        assertEquals(1, categoryList.size());

        // update
        category1.setName("Simple");
        category1.setImage("/re/re.jpg");
        category1.setDescription("fkffkwdkoghrih");
        categoryDao.update(category1);
        Category category3 = categoryDao.findByName("Simple");
        assertEquals(1L, category3.getId());



        //Test Client
        ClientDao clientDao = factory.getClientDao();

        Client client1 = new Client();
        client1.setSurname("Сеник");
        client1.setLastname("Володимирович");
        client1.setAge(20);
        client1.setPhone(346436456);
        client1.setEmail("gogigi@gmail.com");

        clientDao.save(client1);

        List<Client> clientsList = clientDao.findAll();

        assertEquals(1,clientsList.size());

        Client client2 = clientDao.findById(1L);

        assertEquals("Володимирович", client2.getLastname());

        client2.setLastname("Dungeon Master");

        clientDao.update(client2);

        Client client3 = clientDao.findById(1L);
        assertEquals("Dungeon Master", client3.getLastname());

        /*
        Client client4 = clientDao.findByName("Шакал");

        assertEquals("Шакал", client4.getLastname());

        clientDao.delete(client4);
        assertEquals(0, clientDao.findAll().size());
        */

        // Test Order
        OrderDao orderDao = factory.getOrderDao();
        Order order = new Order();
        order.setDate_created(new Date());
        order.setPayment("Почта: Вул. Задовбунського 666");
        order.setTimeOfStay(345);
        orderDao.save(order);

        List<Order> orders = orderDao.findAll();
        assertEquals(1,orders.size());
        Order order1 = orderDao.findById(1L);
        assertEquals("Почта: Вул. Задовбунського 666", order1.getPayment());
        order1.setPayment("Голубом відправте");
        orderDao.update(order1);
        Order order2 = orderDao.findById(1L);
        assertEquals("Голубом відправте", order2.getPayment());
        Order order3 = orderDao.findByName("Голубом відправте");
        assertEquals("Голубом відправте", order3.getPayment());

        // Test Rooms
        RoomsDao roomsDao = factory.getRoomsDao();
        Rooms room = new Rooms();
        room.setName("вапвапв");
        room.setPrice(new BigDecimal(35453.44));
        room.setDescription("впжашщфко");
        room.setImage("/image/room1.jpg");
        roomsDao.save(room);

        List<Rooms> rooms = roomsDao.findAll();
        assertEquals(1,rooms.size());
        Rooms rooms1 = roomsDao.findById(1L);
        assertEquals("вапвапв", rooms1.getName());
        rooms1.setName("Igor");
        roomsDao.update(rooms1);
        Rooms rooms2 = roomsDao.findById(1L);
        assertEquals("Igor", rooms2.getName());
        Rooms rooms3 = roomsDao.findByName("Igor");
        assertEquals("Igor", rooms3.getName());

        // Test RoomsHasOrder
        RoomsHasOrderDao roomsHasOrderDao = factory.getRoomsHasOrderDao();
        RoomsHasOrder rhs = new RoomsHasOrder();
        rhs.setName("Homer");
        roomsHasOrderDao.save(rhs);

        List<RoomsHasOrder> rhs1 = roomsHasOrderDao.findAll();
        assertEquals(1,rhs1.size());
        RoomsHasOrder rhs2 = roomsHasOrderDao.findById(1L);
        assertEquals("Homer", rhs2.getName());
        rhs2.setName("Francisco");
        roomsHasOrderDao.update(rhs2);
        RoomsHasOrder rhs3 = roomsHasOrderDao.findById(1L);
        assertEquals("Francisco", rhs3.getName());
        RoomsHasOrder rhs4 = roomsHasOrderDao.findByName("Francisco");
        assertEquals("Francisco", rhs4.getName());
    }
}
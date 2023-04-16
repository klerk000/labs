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

        categoryDao.saveNativeSQL(category);
        Category category1 = categoryDao.findByIdNativeSQL(1L);
        assertEquals(1L, category1.getId());

        Category category2 = categoryDao.findByNameNativeSQL("Lux");
        assertEquals(1L, category2.getId());

        List<Category> categoryList = categoryDao.findAllNativeSQL();
        assertEquals(1, categoryList.size());

        category1.setName("Simple");
        category1.setImage("/re/re.jpg");
        category1.setDescription("fkffkwdkoghrih");
        categoryDao.updateNativeSQL(category1);
        Category category3 = categoryDao.findByNameNativeSQL("Simple");
        assertEquals(1L, category3.getId());


        //Test Client
        ClientDao clientDao = factory.getClientDao();

        Client client1 = new Client();
        client1.setSurname("Сеник");
        client1.setLastname("Володимирович");
        client1.setAge(20);
        client1.setPhone(346436456);
        client1.setEmail("gogigi@gmail.com");

        clientDao.saveNativeSQL(client1);

        List<Client> clientsList = clientDao.findAllNativeSQL();

        assertEquals(1, clientsList.size());

        Client client2 = clientDao.findByIdNativeSQL(1L);

        assertEquals("Володимирович", client2.getLastname());

        client2.setLastname("Dungeon Master");

        clientDao.updateNativeSQL(client2);

        Client client3 = clientDao.findByIdNativeSQL(1L);
        assertEquals("Dungeon Master", client3.getLastname());

        Client client4 = clientDao.findByNameNativeSQL("Dungeon Master");
        assertEquals("Dungeon Master", client4.getLastname());

        clientDao.deleteNativeSQL(client4);
        assertEquals(0, clientDao.findAllNativeSQL().size());


        // Test Order
        OrderDao orderDao = factory.getOrderDao();
        Order order = new Order();
        order.setDate_created(new Date());
        order.setPayment("Почта: Вул. Задовбунського 666");
        order.setTimeOfStay(345);
        orderDao.saveNativeSQL(order);

        List<Order> orders = orderDao.findAllNativeSQL();
        assertEquals(1,orders.size());
        Order order1 = orderDao.findByIdNativeSQL(1L);
        assertEquals("Почта: Вул. Задовбунського 666", order1.getPayment());
        order1.setPayment("Голубом відправте");
        orderDao.updateNativeSQL(order1);
        Order order2 = orderDao.findByIdNativeSQL(1L);
        assertEquals("Голубом відправте", order2.getPayment());
        Order order3 = orderDao.findByNameNativeSQL("Голубом відправте");
        assertEquals("Голубом відправте", order3.getPayment());

        // Test Rooms
        RoomsDao roomsDao = factory.getRoomsDao();
        Rooms room = new Rooms();
        room.setName("вапвапв");
        room.setPrice(new BigDecimal(35453.5));
        room.setDescription("впжашщфко");
        room.setImage("/image/room1.jpg");
        roomsDao.saveNativeSQL(room);

        List<Rooms> rooms = roomsDao.findAllNativeSQL();
        assertEquals(1,rooms.size());
        Rooms rooms1 = roomsDao.findByIdNativeSQL(1L);
        assertEquals("вапвапв", rooms1.getName());
        rooms1.setName("Igor");
        roomsDao.updateNativeSQL(rooms1);
        Rooms rooms2 = roomsDao.findByIdNativeSQL(1L);
        assertEquals("Igor", rooms2.getName());
        Rooms rooms3 = roomsDao.findByNameNativeSQL("Igor");
        assertEquals("Igor", rooms3.getName());

        // Test RoomsHasOrder
        RoomsHasOrderDao roomsHasOrderDao = factory.getRoomsHasOrderDao();
        RoomsHasOrder rhs = new RoomsHasOrder();
        rhs.setName("Homer");
        roomsHasOrderDao.saveNativeSQL(rhs);

        List<RoomsHasOrder> rhs1 = roomsHasOrderDao.findAllNativeSQL();
        assertEquals(1,rhs1.size());
        RoomsHasOrder rhs2 = roomsHasOrderDao.findByIdNativeSQL(1L);
        assertEquals("Homer", rhs2.getName());
        rhs2.setName("Francisco");
        roomsHasOrderDao.updateNativeSQL(rhs2);
        RoomsHasOrder rhs3 = roomsHasOrderDao.findByIdNativeSQL(1L);
        assertEquals("Francisco", rhs3.getName());
        RoomsHasOrder rhs4 = roomsHasOrderDao.findByNameNativeSQL("Francisco");
        assertEquals("Francisco", rhs4.getName());
    }

    @Test
    public void Test2(){

        //Test Category
        CategoryDao categoryDao = factory.getCategoryDao();

        Category category = new Category();

        category.setName("Lux");
        category.setDescription("Over price rooms");
        category.setImage("/images/lux.jpg");

        categoryDao.saveHQL(category);
        Category category1 = categoryDao.findByIdHQL(2L);
        assertEquals(2L, category1.getId());

        Category category2 = categoryDao.findByNameHQL("Lux");
        assertEquals(2L, category2.getId());

        List<Category> categoryList = categoryDao.findAllHQL();
        assertEquals(2, categoryList.size());

        category1.setName("Siimple");
        category1.setImage("/re/re.jpg");
        category1.setDescription("fkffkwdkoghrih");
        categoryDao.updateHQL(category1);
        Category category3 = categoryDao.findByNameHQL("Siimple");
        assertEquals(2L, category3.getId());


        //Test Client
        ClientDao clientDao = factory.getClientDao();

        Client client1 = new Client();
        client1.setSurname("Сеник");
        client1.setLastname("Володимирович");
        client1.setAge(20);
        client1.setPhone(346436456);
        client1.setEmail("gogigi@gmail.com");

        clientDao.saveHQL(client1);

        List<Client> clientsList = clientDao.findAllHQL();

        assertEquals(1, clientsList.size());

        Client client2 = clientDao.findByIdHQL(2L);

        assertEquals("Володимирович", client2.getLastname());

        client2.setLastname("Dungeon Master");

        clientDao.updateHQL(client2);

        Client client3 = clientDao.findByIdHQL(2L);
        assertEquals("Dungeon Master", client3.getLastname());

        Client client4 = clientDao.findByNameHQL("Dungeon Master");
        assertEquals("Dungeon Master", client4.getLastname());

        clientDao.deleteHQL(client4);
        assertEquals(0, clientDao.findAllHQL().size());


        // Test Order
        OrderDao orderDao = factory.getOrderDao();
        Order order = new Order();
        order.setDate_created(new Date());
        order.setPayment("Почта: Вул. Задовбунського 666");
        order.setTimeOfStay(345);
        orderDao.saveHQL(order);

        List<Order> orders = orderDao.findAllHQL();
        assertEquals(2,orders.size());

        Order order1 = orderDao.findByIdHQL(2L);
        assertEquals("Почта: Вул. Задовбунського 666", order1.getPayment());
        order1.setPayment("Голубом відправте");
        orderDao.updateHQL(order1);
        Order order2 = orderDao.findByIdHQL(2L);
        assertEquals("Голубом відправте", order2.getPayment());
        Order order3 = orderDao.findByNameHQL("Голубом відправте");
        assertEquals("Голубом відправте", order3.getPayment());

        // Test Rooms
        RoomsDao roomsDao = factory.getRoomsDao();
        Rooms room = new Rooms();
        room.setName("вапвапв");
        room.setPrice(new BigDecimal(35453.5));
        room.setDescription("впжашщфко");
        room.setImage("/image/room1.jpg");
        roomsDao.saveHQL(room);

        List<Rooms> rooms = roomsDao.findAllHQL();
        assertEquals(2,rooms.size());
        Rooms rooms1 = roomsDao.findByIdHQL(2L);
        assertEquals("вапвапв", rooms1.getName());
        rooms1.setName("Igor");
        roomsDao.updateHQL(rooms1);
        Rooms rooms2 = roomsDao.findByIdHQL(2L);
        assertEquals("Igor", rooms2.getName());
        Rooms rooms3 = roomsDao.findByNameHQL("Igor");
        assertEquals("Igor", rooms3.getName());

        // Test RoomsHasOrder
        RoomsHasOrderDao roomsHasOrderDao = factory.getRoomsHasOrderDao();
        RoomsHasOrder rho = new RoomsHasOrder();
        rho.setName("Homer");
        roomsHasOrderDao.saveHQL(rho);

        List<RoomsHasOrder> rhs1 = roomsHasOrderDao.findAllHQL();
        assertEquals(2,rhs1.size());
        RoomsHasOrder rhs2 = roomsHasOrderDao.findByIdHQL(2L);
        assertEquals("Homer", rhs2.getName());
        rhs2.setName("Francisco");
        roomsHasOrderDao.updateHQL(rhs2);
        RoomsHasOrder rhs3 = roomsHasOrderDao.findByIdHQL(2L);
        assertEquals("Francisco", rhs3.getName());
        RoomsHasOrder rhs4 = roomsHasOrderDao.findByNameHQL("Francisco");
        assertEquals("Francisco", rhs4.getName());
    }
}
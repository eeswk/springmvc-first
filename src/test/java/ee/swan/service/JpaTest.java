package ee.swan.service;

import ee.swan.config.JpaConfig;
import ee.swan.domain.Room;
import javax.persistence.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class JpaTest {

    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void setUp() {
        Room room = new Room();
        room.setRoomName("D클래스");
        room.setCapacity(10);
        entityManager.persist(room);

    }

    @Test
    @Transactional(readOnly = true)
    @Rollback(false)
    public void testGetEquipmentsInRoom1() {

        Room room1 = entityManager.find(Room.class, 1);
        System.out.println(room1.getRoomName());
    }


    @Test
    public void testGetEquipmentsInRoom() {
        EntityTransaction tx = entityManager.getTransaction();
           try {
               tx.begin();
               Room room = new Room();
               room.setRoomName("B클래스");
               room.setCapacity(10);
               entityManager.persist(room);
               tx.commit();
           } catch (Exception e) {
               tx.rollback();
           }

        Room room1 = entityManager.find(Room.class, 1);
        System.out.println(room1.getRoomName());
    }


    @Test
    public void testCreateRoom() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Room room = new Room();
            room.setRoomName("A클래스");
            room.setCapacity(10);
            em.persist(room);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

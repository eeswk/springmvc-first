package ee.swan.service;

import ee.swan.config.JpaConfig;
import ee.swan.domain.Room;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class RoomServiceImplTest {

    RoomService roomService;

    @Mock
    EntityManager entityManager;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        System.out.println("entityManager=>" + entityManager);
        //roomService = new RoomServiceImpl();
        roomService = new RoomServiceImpl(entityManager);
    }

    @Test
    @Transactional
    public void testGetRoom() {
        Room mockRoom = new Room();
        //mockRoom.setRoomId(1);
        mockRoom.setRoomName("자료실");
        mockRoom.setCapacity(5);

        //entityManager.persist(mockRoom);
        given(roomService.getRoom(2)).willReturn(mockRoom);
        System.out.println(mockRoom.getRoomId());

        Room resultRoom = roomService.getRoom(2);
        System.out.println(resultRoom.getRoomId());
        //Room resultRoom = roomService.getRoom(mockRoom.getRoomId());

        assertThat(resultRoom.getRoomName(), is(mockRoom.getRoomName()));
        assertThat(resultRoom.getCapacity(), is(mockRoom.getCapacity()));
    }

    @Test
    public void testGetRoomByName() {
        String roomName = "Room A";
        List<Room> room = roomService.getRoomsByFetch(roomName);
        System.out.println(room.size());
    }


}
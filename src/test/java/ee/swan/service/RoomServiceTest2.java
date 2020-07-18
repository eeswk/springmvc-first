package ee.swan.service;

import ee.swan.config.JpaConfig;
import ee.swan.domain.Room;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class RoomServiceTest2 {
    Room mockRoom;

    EntityManager entityManager;

    RoomServiceImpl roomService;

    @Before
    public void setUp() {
        mockRoom = new Room();
        //mockRoom.setRoomId(1);
        mockRoom.setRoomName("자료실");
        mockRoom.setCapacity(5);
        entityManager = mock(EntityManager.class);
        System.out.println("em=>"+entityManager);
        roomService = new RoomServiceImpl(entityManager);
    }

    @Test
    public void get() {
        when(entityManager.find(Room.class,1)).thenReturn(mockRoom);
        System.out.println("mockRoom=>" + mockRoom.getRoomName());
        Room room = roomService.getRoom(1);
        System.out.println("room=>" + room.getRoomName());
        assertThat(room.getRoomName(), is(mockRoom.getRoomName()));
    }
}

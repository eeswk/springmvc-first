package ee.swan.service;

import ee.swan.config.JpaConfig;
import ee.swan.domain.Room;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@Transactional
public class RoomServiceTest2 {
    Room mockRoom;

    EntityManager entityManager;

    RoomServiceImpl roomService;

    Query query;
    TypedQuery<Room> typedQuery;


    @Before
    public void setUp() {
        mockRoom = new Room();
        //mockRoom.setRoomId(1);
        mockRoom.setRoomName("자료실");
        mockRoom.setCapacity(5);
        entityManager = mock(EntityManager.class);
        System.out.println("em=>"+entityManager);
        roomService = new RoomServiceImpl(entityManager);
        query = mock(Query.class);
        typedQuery = mock(TypedQuery.class);
    }

    @Test
    public void testGetRoom() {
        when(entityManager.find(Room.class,1)).thenReturn(mockRoom);
        System.out.println("mockRoom=>" + mockRoom.getRoomName());
        Room room = roomService.getRoom(1);
        System.out.println("room=>" + room.getRoomName());
        assertThat(room.getRoomName(), is(mockRoom.getRoomName()));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCreateRoom() {
        Room newRoom = roomService.createRoom(mockRoom.getRoomName(), mockRoom.getCapacity());
        System.out.println(newRoom.getRoomName());

        assertThat(newRoom.getRoomName(), is(mockRoom.getRoomName()));
    }




    @Test
    public void testGetRoomByName() {
        String roomName = "자료실";
        String jpql =
                "SELECT DISTINCT r FROM Room r "
                + " LEFT JOIN FETCH r.equipments "
                + " WHERE r.roomName = :roomName";
        System.out.println(typedQuery);

        //when(entityManager.createQuery(anyString(), Room.class)).thenReturn(typedQuery);
        when(entityManager.createQuery(jpql, Room.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("roomName", roomName)).thenReturn(typedQuery);

        System.out.println(typedQuery);

        List<Room> mockList = new ArrayList<>();
        mockList.add(mockRoom);
        when(typedQuery.getResultList()).thenReturn(mockList);

        List<Room> room = roomService.getRoomsByFetch(roomName);

        //verify(roomService.getRoomsByFetch(roomName));
        System.out.println(room.size());
    }

}

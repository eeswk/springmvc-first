package ee.swan.service;

import ee.swan.domain.Room;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RoomServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional(readOnly = true)
    public Room getRoom(Integer id) {
        Room room = entityManager.find(Room.class, id);
        if (room == null) {
            //대상이 없을때 처리
            return null;
        }
        return room;
    }

    @Transactional
    public Room createRoom(String roomName, Integer capacity) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setCapacity(capacity);
        entityManager.persist(room);
        return room;
    }

    @Transactional
    public Room updateRoomName(Integer id, String roomName) {
        Room room = entityManager.find(Room.class, id);
        room.setRoomName(roomName);
        return room;
    }

    @Transactional
    public void deleteRoom(Integer id) {
        Room room = getRoom(id);
        entityManager.remove(room);
    }


}

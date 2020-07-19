package ee.swan.service;

import ee.swan.domain.Room;
import java.util.List;
import javax.persistence.*;
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

    @Transactional(readOnly = true)
    public List<Room> getRoomsByFetch(String roomName) {
        String jpql = "SELECT DISTINCT r FROM Room r "
                + " LEFT JOIN FETCH r.equipments "
                + " WHERE r.roomName = :roomName";
        TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
        query.setParameter("roomName", roomName);
        return query.getResultList();
    }

    @Transactional
    public Integer updateCapacityAll(Integer capacity) {
        String jpql = "UPDATE Room r SET r.capacity = :capacity";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("capacity", capacity);
        return query.executeUpdate();
    }

}

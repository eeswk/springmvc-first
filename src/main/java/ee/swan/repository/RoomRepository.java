package ee.swan.repository;

import ee.swan.domain.Equipment;
import ee.swan.domain.Room;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Equipment> getEquipmentsInRoom(Integer roomId) {
        Room room = entityManager.find(Room.class, roomId);
        return room.getEquipments();
    }

    @Transactional(readOnly = true)
    public Room getRoomOfEquipment(Integer equipmentId) {
        Equipment equipment = entityManager.find(Equipment.class, equipmentId);
        return equipment.getRoom();
    }

    @Transactional(readOnly = true)
    public List<Room> getRoomByName(String roomName) {
        String jpql = "SELECT r FROM Room r WHERE r.roomName = :roomName";
        TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
        query.setParameter("roomName", roomName);
        return query.getResultList();
    }
}

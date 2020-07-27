package ee.swan.repository;

import ee.swan.domain.Room;
import ee.swan.domain.RoomCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class RoomRepositoryImpl implements RoomRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findByCriteria(RoomCriteria criteria) {
        String jpql = "SELECT r FROM Room r WHERE r.roomName = :roomName";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        query.setParameter("roomName", criteria.getRoomName());
        List<Room> rooms = query.getResultList();
        return rooms;
    }
}

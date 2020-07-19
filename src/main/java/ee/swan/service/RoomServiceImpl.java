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

    @Transactional
    public void updateRoomWithOptimisticLock(Integer id, String roomName, Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        entityManager.lock(room, LockModeType.OPTIMISTIC);
        //갱신 처리생략
        // 낙관적 잠금이 실패한 경우 트랜잭션이 종료되는 시점에 OptimisticLockException이 발생한다.
    }

    @Transactional
    public void updateRoomWithPessimisticLock(Integer id, String roomName, Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        try {
            entityManager.lock(room, LockModeType.PESSIMISTIC_READ);
        } catch (PessimisticLockException e) {
            //락을 거는 과정에서 실패한경우
            //생략
        } catch (LockTimeoutException e) {
            // 락을 거는 과정에서 시간이 초과한 경우(트랜잭션 자체는 롤백되지 않음)
        }
        //갱신처리(생략
    }

}

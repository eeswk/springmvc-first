package ee.swan.repository;

import ee.swan.domain.Room;
import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomSDJRespository extends JpaRepository<Room, Integer>, RoomRepositoryCustom {

    @Query("SELECT r FROM Room r WHERE r.roomName = :roomName")
    List<Room> findByRoomName(@Param("roomName") String roomName);

    @Query("UPDATE Room r SET r.capacity = :capacity")
    Integer updateCapacityAll(@Param("capacity") Integer capacity);

    List<Room> findByRoomNameAndCapacity(String roomName, Integer capacity);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Room> findAll();


    @Query("SELECT r FROM Room r WHERE r.roomName = :roomName")
    Page<Room> findByRoomName(@Param("roomName") String roomName, Pageable pageable);
}

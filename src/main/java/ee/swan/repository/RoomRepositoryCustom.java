package ee.swan.repository;

import ee.swan.domain.Room;
import ee.swan.domain.RoomCriteria;
import java.util.List;

public interface RoomRepositoryCustom{
    List<Room> findByCriteria(RoomCriteria criteria);
}

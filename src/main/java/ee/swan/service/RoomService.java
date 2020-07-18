package ee.swan.service;

import ee.swan.domain.Room;
import javax.persistence.EntityManager;

public interface RoomService {

    public Room getRoom(Integer id);
    public Room createRoom(String roomName, Integer capacity);
    public Room updateRoomName(Integer id, String roomName);
    public void deleteRoom(Integer id);


}

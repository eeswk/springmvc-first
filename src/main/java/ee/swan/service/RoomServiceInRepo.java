package ee.swan.service;

import ee.swan.domain.Room;
import ee.swan.repository.RoomRepository;
import ee.swan.repository.RoomSDJRespository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceInRepo implements RoomService {

    @Autowired
    RoomSDJRespository roomSDJRespository;


    @Override
    @Transactional(readOnly = true)
    public Room getRoom(Integer id) {
        Room room = roomSDJRespository.findOne(id);
        if (room == null) {

        }
        return room;
    }
    @Transactional(readOnly = true)
    public List<Room> getRoomsAll() {
        return roomSDJRespository.findAll(new Sort(Sort.Direction.ASC,"roomId"));
    }

    @Override
    @Transactional
    public Room createRoom(String roomName, Integer capacity) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setCapacity(capacity);
        return roomSDJRespository.save(room);
    }

    @Override
    @Transactional
    public Room updateRoomName(Integer id, String roomName) {
        Room room = getRoom(id);
        room.setRoomName(roomName);
        return room;
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) {
        roomSDJRespository.delete(id);
    }

    @Override
    public List<Room> getRoomsByFetch(String roomName) {
        return null;
    }
}

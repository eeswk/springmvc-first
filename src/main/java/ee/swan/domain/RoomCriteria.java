package ee.swan.domain;

public class RoomCriteria {

    private String roomName;
    private Integer capacity;

    public RoomCriteria() {
    }

    public RoomCriteria(String roomName, Integer capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }
}


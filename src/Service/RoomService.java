package Service;

import Entity.Room;
import Entity.RoomType;

import java.util.List;
import java.util.Map;

public class RoomService {
    public static List<Room> roomList;

    public RoomService() {
        ;
    }

    public static Room getRoom(RoomType roomType) {
        return roomList.stream().filter(r -> r.getRoomType() == roomType).toList().get(0);
    }

}

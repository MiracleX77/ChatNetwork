package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.model.MChatRoomResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoomMapper {
    MChatRoomResponse toChatRoomResponse(Room room);
}

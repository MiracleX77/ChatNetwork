package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.model.MChatRoomResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T20:36:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public MChatRoomResponse toChatRoomResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        MChatRoomResponse mChatRoomResponse = new MChatRoomResponse();

        return mChatRoomResponse;
    }
}

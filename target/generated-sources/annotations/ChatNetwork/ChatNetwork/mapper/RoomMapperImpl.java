package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.model.MChatRoomResponse;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T23:35:30+0700",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.6 (Eclipse Adoptium)"
)
*/
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public MChatRoomResponse toChatRoomResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        MChatRoomResponse mChatRoomResponse = new MChatRoomResponse();

        mChatRoomResponse.setReceiver( room.getReceiver() );

        return mChatRoomResponse;
    }
}

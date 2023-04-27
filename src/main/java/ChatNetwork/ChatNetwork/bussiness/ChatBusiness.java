package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.ChatException;
import ChatNetwork.ChatNetwork.mapper.RoomMapper;
import ChatNetwork.ChatNetwork.model.*;
import ChatNetwork.ChatNetwork.service.ChatService;
import ChatNetwork.ChatNetwork.utill.SecurityUtill;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatBusiness {
    private final SimpMessagingTemplate template;

    private final ChatService chatService;
    private final RoomMapper roomMapper;

    public ChatBusiness(SimpMessagingTemplate template, ChatService chatService, RoomMapper roomMapper) {
        this.template = template;
        this.chatService = chatService;
        this.roomMapper = roomMapper;
    }

    public void post(MChatMessageRequest request) throws BaseException{
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        final String  destination = "/topic/chat";

        MChatMessage payload = new MChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());
        template.convertAndSend(destination,payload);
    }

    public void createRoom(MChatRoomRequest request) throws BaseException{
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        String s = chatService.createRoom(opt.get(),request.getName());
        if (s.isEmpty()){
            throw ChatException.accessDenied();
        }
        final String  destination = "/topic/create-room";
        MChatRoomResponse payload = new MChatRoomResponse();
        payload.setNameTalker(s);
        template.convertAndSend(destination,payload);
    }

    public MChatRoomsResponse getRooms() throws BaseException{
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        MChatRoomsResponse response = new MChatRoomsResponse();
        List<MChatRoomResponse> roomList = response.getRooms();
        List<Room> rooms =  chatService.getRoomsOfUser(opt.get());
        for (Room room : rooms) {
            roomList.add(roomMapper.toChatRoomResponse(room));
        }
        response.setRooms(roomList);
        return response;

    }
}

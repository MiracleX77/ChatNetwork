package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.entity.Message;
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
        chatService.setMessageToRoom(opt.get(), request.getReceiver(), request.getMessage());


        final String  destination = "/topic/chat";
        MChatMessage payload = new MChatMessage();
        payload.setReceiver(request.getReceiver());
        payload.setMessage(request.getMessage());
        System.out.println(payload.getMessage());
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
        payload.setReceiver(s);
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
            MChatRoomResponse mChatRoomResponse = new MChatRoomResponse();
            mChatRoomResponse.setReceiver(room.getReceiver());
            roomList.add(mChatRoomResponse);
        }

        response.setRooms(roomList);

        return response;

    }
    public MChatName getName() throws BaseException{
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        String res = chatService.getName(opt.get());
        MChatName mChatName = new MChatName();
        mChatName.setName(res);
        return mChatName;

    }
    public MChatMessages getMessages(String receiver) throws ChatException {
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        Room room = chatService.getRoomOfUserAndReceiver(opt.get(),receiver);
        MChatMessages messages = new MChatMessages();
        List<MChatMessage> mChatMessages = messages.getMessages();

        List<Message> messageList = room.getMessages();
        for (Message mes : messageList){
            MChatMessage message = new MChatMessage();
            message.setReceiver(mes.getReceiver());
            message.setMessage(mes.getMessage());
            message.setCreated(mes.getCreated());
            mChatMessages.add(message);
        }
        messages.setMessages(mChatMessages);
        return messages;
    }
}

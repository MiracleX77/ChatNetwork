package ChatNetwork.ChatNetwork.api;

import ChatNetwork.ChatNetwork.bussiness.ChatBusiness;
import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.model.*;
import ChatNetwork.ChatNetwork.repository.ChatRepository;
import ChatNetwork.ChatNetwork.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/chat")
public class ChatApi {
    private final ChatBusiness chatBusiness;

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatApi(ChatBusiness chatBusiness, ChatRepository chatRepository, UserRepository userRepository) {
        this.chatBusiness = chatBusiness;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody MChatMessageRequest request) throws BaseException {
        chatBusiness.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create-room")
    public ResponseEntity<Void> createRoom(@RequestBody MChatRoomRequest request) throws BaseException{
        chatBusiness.createRoom(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/get-rooms")
    public ResponseEntity<MChatRoomsResponse> getRooms() throws BaseException{
        MChatRoomsResponse response = chatBusiness.getRooms();
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/get-name")
    public ResponseEntity<MChatName> getNameOfUser() throws BaseException{
        MChatName response = chatBusiness.getName();
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/get-messages-{receiver}")
    public ResponseEntity<MChatMessages> getMessagesOfRoom(@PathVariable("receiver") String request) throws BaseException{
        MChatMessages response = chatBusiness.getMessages(request);
        return ResponseEntity.ok(response);
    }





}

package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.ChatException;
import ChatNetwork.ChatNetwork.model.MChatMessage;
import ChatNetwork.ChatNetwork.model.MChatMessageRequest;
import ChatNetwork.ChatNetwork.model.MChatRoomRequest;
import ChatNetwork.ChatNetwork.model.MChatRoomResponse;
import ChatNetwork.ChatNetwork.service.ChatService;
import ChatNetwork.ChatNetwork.utill.SecurityUtill;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatBusiness {
    private final SimpMessagingTemplate template;

    private final ChatService chatService;

    public ChatBusiness(SimpMessagingTemplate template, ChatService chatService) {
        this.template = template;
        this.chatService = chatService;
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

    public MChatRoomResponse createRoom(MChatRoomRequest request) throws BaseException{
        Optional<Long> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        boolean s = chatService.createRoom(opt.get(),request.getName());
        if (!s){
            throw ChatException.accessDenied();
        }
        MChatRoomResponse status = new MChatRoomResponse();
        status.setStatus(true);
        return status;




    }
}

package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.ChatException;
import ChatNetwork.ChatNetwork.model.MChatMessage;
import ChatNetwork.ChatNetwork.model.MChatMessageRequest;
import ChatNetwork.ChatNetwork.utill.SecurityUtill;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatBusiness {
    private final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(MChatMessageRequest request) throws BaseException{
        Optional<String> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        final String  destination = "/topic/chat";

        MChatMessage payload = new MChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());
        template.convertAndSend(destination,payload);
    }

    public void getNameUser() throws BaseException{
        Optional<String> opt = SecurityUtill.getCurrentUserId();


    }
}

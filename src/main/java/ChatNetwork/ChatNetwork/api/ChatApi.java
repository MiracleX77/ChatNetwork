package ChatNetwork.ChatNetwork.api;

import ChatNetwork.ChatNetwork.bussiness.ChatBusiness;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.model.MChatMessageRequest;
import ChatNetwork.ChatNetwork.model.MChatNameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatApi {
    private final ChatBusiness chatBusiness;

    public ChatApi(ChatBusiness chatBusiness) {
        this.chatBusiness = chatBusiness;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody MChatMessageRequest request) throws BaseException {
        chatBusiness.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
//    @GetMapping("/name")
//    public ResponseEntity<MChatNameResponse> getNameUser() throws BaseException{
//        MChatNameResponse nameResponse = chatBusiness.
//        return ResponseEntity.ok(M)
//    }
}

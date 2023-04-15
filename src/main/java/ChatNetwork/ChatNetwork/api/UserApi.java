package ChatNetwork.ChatNetwork.api;

import ChatNetwork.ChatNetwork.bussiness.UserBusiness;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.model.MLoginRequest;
import ChatNetwork.ChatNetwork.model.MLoginResponse;
import ChatNetwork.ChatNetwork.model.MRegisterRequest;
import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/login")
    public ResponseEntity<MLoginResponse> login (@RequestBody MLoginRequest request) throws BaseException {
        MLoginResponse  response = userBusiness.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<MRegisterResponse> register (@RequestBody MRegisterRequest request) throws BaseException{
        MRegisterResponse response = userBusiness.register(request);
        return ResponseEntity.ok(response);
    }
}

package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.mapper.UserMapper;
import ChatNetwork.ChatNetwork.model.MLoginRequest;
import ChatNetwork.ChatNetwork.model.MLoginResponse;
import ChatNetwork.ChatNetwork.model.MRegisterRequest;
import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import ChatNetwork.ChatNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
@Service
public class UserBusiness {
    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public MLoginResponse login(MLoginRequest request) throws BaseException{
        MLoginResponse response = new MLoginResponse();
        return response;
    }
    public MRegisterResponse register(MRegisterRequest request) throws BaseException{
        User user = userService.create(request.getEmail(),request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);
    }


}

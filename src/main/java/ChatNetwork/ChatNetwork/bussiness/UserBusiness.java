package ChatNetwork.ChatNetwork.bussiness;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.UserException;
import ChatNetwork.ChatNetwork.mapper.UserMapper;
import ChatNetwork.ChatNetwork.model.MLoginRequest;
import ChatNetwork.ChatNetwork.model.MLoginResponse;
import ChatNetwork.ChatNetwork.model.MRegisterRequest;
import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import ChatNetwork.ChatNetwork.service.TokenService;
import ChatNetwork.ChatNetwork.service.UserService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;

    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public MLoginResponse login(MLoginRequest request) throws BaseException{
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginEmailNotFound();
        }
        User user = opt.get();
        if(!user.getPassword().equals(request.getPassword())){
            throw UserException.loginPasswordIncorrect();
        }

        MLoginResponse response = new MLoginResponse();
        response.setToken(tokenService.tokenize(user));
        return response;
    }
    public MRegisterResponse register(MRegisterRequest request) throws BaseException{
        User user = userService.create(request.getEmail(),request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);
    }


}

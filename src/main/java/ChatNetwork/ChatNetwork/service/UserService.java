package ChatNetwork.ChatNetwork.service;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.UserException;
import ChatNetwork.ChatNetwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String email,String password,String name) throws BaseException{
        if(Objects.isNull(email)){
            throw UserException.createEmailNull();
        }
        if(Objects.isNull(password)){
            throw UserException.createPasswordNull();
        }
        if(Objects.isNull(name)){
            throw UserException.createNameNull();
        }
        if(userRepository.existsByEmail(email)){
            throw UserException.verifyEmail();
        }
        User entity = new User();
        entity.setEmail(email);
        entity.setName(name);
        entity.setPassword(password);
        return userRepository.save(entity);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}

package ChatNetwork.ChatNetwork.service;

import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.ChatException;
import ChatNetwork.ChatNetwork.repository.ChatRepository;
import ChatNetwork.ChatNetwork.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }
    public boolean createRoom(Long userIdRequest, String name) throws BaseException {
        Optional<User> opt1 = userRepository.findById(userIdRequest);
        if(opt1.isEmpty()){
            throw ChatException.createRoomDenied();
        }
        Optional<User> opt2 = userRepository.findByName(name);
        if(opt2.isEmpty()){
            throw ChatException.createRoomNotFound();
        }

        User user1 = opt1.get();
        User user2 = opt2.get();

        Room r1 = new Room();
        r1.setTalkername(user2.getName());
        r1.setTalkerid(user2.getId());
        user1.getRooms().add(r1);
        Room r2 = new Room();
        r2.setTalkername(user1.getName());
        r2.setTalkerid(user1.getId());
        user2.getRooms().add(r2);

        userRepository.save(user1);
        userRepository.save(user2);

        return true;
    }

}

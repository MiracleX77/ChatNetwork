package ChatNetwork.ChatNetwork.service;

import ChatNetwork.ChatNetwork.entity.Message;
import ChatNetwork.ChatNetwork.entity.Room;
import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.exception.ChatException;
import ChatNetwork.ChatNetwork.model.MChatMessage;
import ChatNetwork.ChatNetwork.repository.ChatRepository;
import ChatNetwork.ChatNetwork.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    public String createRoom(Long userIdRequest, String name) throws BaseException {
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
        r1.setReceiver(user2.getName());
        r1.setReceiverid(user2.getId());
        user1.getRooms().add(r1);
        Room r2 = new Room();
        r2.setReceiver(user1.getName());
        r2.setReceiverid(user1.getId());
        user2.getRooms().add(r2);
        userRepository.save(user1);
        userRepository.save(user2);
        return name;
    }
    public List<Room> getRoomsOfUser(Long userId) throws BaseException{
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isEmpty()){
            throw ChatException.createRoomDenied();
        }
        User user = opt.get();
        return user.getRooms();

    }

    public void setMessageToRoom(Long senderId,String receiverName,String message) throws BaseException{
        Optional<User> opt1 = userRepository.findById(senderId);
        if(opt1.isEmpty()){
            throw ChatException.senderIdNotFound();
        }
        Optional<User> opt2 = userRepository.findByName(receiverName);
        if(opt2.isEmpty()){
            throw ChatException.receiverIdNotFound();
        }
        User user1 = opt1.get();
        User user2 = opt2.get();

        Message message1 = new Message();
        message1.setMessage(message);
        message1.setSender(user1.getName());
        message1.setReceiver(user2.getName());
        for(Room room:user1.getRooms()){
            if(Objects.equals(room.getReceiver(), receiverName)){
                room.getMessages().add(message1);
                chatRepository.save(room);
                break;
            }

        }
        for(Room room1:user2.getRooms()){
            if(Objects.equals(room1.getReceiver(), user1.getName())){
                room1.getMessages().add(message1);
                chatRepository.save(room1);
                break;
            }
        }
    }
    public String getName(Long userId) throws BaseException {
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isEmpty()){
            throw ChatException.senderIdNotFound();
        }
        return opt.get().getName();
    }

    public Room getRoomOfUserAndReceiver(Long userId,String receiverName) throws ChatException {
        Optional<User> opt1 = userRepository.findById(userId);
        if(opt1.isEmpty()){
            throw ChatException.senderIdNotFound();
        }
        Optional<User> opt2 = userRepository.findByName(receiverName);
        if(opt2.isEmpty()){
            throw ChatException.receiverIdNotFound();
        }
        User user1 = opt1.get();
        User user2 = opt2.get();
        for(Room room:user1.getRooms()){
            if(Objects.equals(room.getReceiver(), receiverName)){
                return room;
            }
        }
        throw ChatException.accessDenied();
    }


}

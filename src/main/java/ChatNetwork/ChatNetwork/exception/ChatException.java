package ChatNetwork.ChatNetwork.exception;

public class ChatException extends BaseException{
    public ChatException(String code) {
        super("Chat"+code);
    }
    public static ChatException accessDenied(){
        return new ChatException("access.denied");
    }
    public static ChatException createRoomDenied(){
        return new ChatException("create.room.denied");
    }
    public static ChatException createRoomNotFound(){
        return new ChatException("create.room.name.not.found");
    }

    public static ChatException senderIdNotFound(){
        return new ChatException("sender.id.not.found");
    }
    public static ChatException receiverIdNotFound(){
        return new ChatException("receiver.id.not.found");
    }

}

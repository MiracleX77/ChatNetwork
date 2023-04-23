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

}

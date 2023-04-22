package ChatNetwork.ChatNetwork.exception;

public class ChatException extends BaseException{
    public ChatException(String code) {
        super("Chat"+code);
    }
    public static ChatException accessDenied(){
        return new ChatException("access.denied");
    }

}

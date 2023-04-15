package ChatNetwork.ChatNetwork.exception;

public class UserException extends BaseException{

    public UserException(String code) {
        super("user." + code);
    }

    //LOGIN
    public static UserException loginEmailNotFound(){
        return new UserException("login.email.not.found");
    }
    public static UserException loginPasswordIncorrect(){
        return new UserException("login.password.incorrect");
    }
    //Register

    public static  UserException createEmailNull(){
        return new UserException("create.email.null");
    }
    public static  UserException createPasswordNull(){
        return new UserException("create.password.null");
    }
    public static  UserException createNameNull(){
        return new UserException("create.name.null");
    }

    public static UserException verifyEmail(){
        return new UserException("verify.email");
    }
}

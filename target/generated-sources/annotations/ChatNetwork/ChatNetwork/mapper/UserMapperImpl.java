package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T19:53:30+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
*/
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public MRegisterResponse toRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        MRegisterResponse mRegisterResponse = new MRegisterResponse();

        return mRegisterResponse;
    }
}

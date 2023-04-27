package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T23:35:30+0700",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.6 (Eclipse Adoptium)"
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

        mRegisterResponse.setEmail( user.getEmail() );
        mRegisterResponse.setName( user.getName() );

        return mRegisterResponse;
    }
}

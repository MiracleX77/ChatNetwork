package ChatNetwork.ChatNetwork.mapper;

import ChatNetwork.ChatNetwork.entity.User;

import ChatNetwork.ChatNetwork.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}

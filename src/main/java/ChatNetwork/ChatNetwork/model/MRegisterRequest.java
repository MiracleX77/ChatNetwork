package ChatNetwork.ChatNetwork.model;

import lombok.Data;

@Data
public class MRegisterRequest {
    private String email;
    private String password;
    private String name;
}

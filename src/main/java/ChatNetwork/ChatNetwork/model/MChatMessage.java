package ChatNetwork.ChatNetwork.model;

import lombok.Data;

import java.util.Date;

@Data
public class MChatMessage {


    private String receiver;

    private String message;

    private Date created;

    public MChatMessage(){
        created = new Date();
    }
}

package ChatNetwork.ChatNetwork.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MChatMessages {
    private List<MChatMessage> messages = new ArrayList<>();

}

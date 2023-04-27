package ChatNetwork.ChatNetwork.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class MChatRoomsResponse {
    private List<MChatRoomResponse> rooms = new ArrayList<>();
}

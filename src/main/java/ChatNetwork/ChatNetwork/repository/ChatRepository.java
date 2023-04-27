package ChatNetwork.ChatNetwork.repository;

import ChatNetwork.ChatNetwork.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Room,Long> {

    Room findRoomByReceiver(@Param("receiver") String receiver);
}

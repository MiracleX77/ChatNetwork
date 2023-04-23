package ChatNetwork.ChatNetwork.repository;

import ChatNetwork.ChatNetwork.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Room,Long> {
}

package ChatNetwork.ChatNetwork.repository;

import ChatNetwork.ChatNetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
    boolean existsByEmail(String email);
}

package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="room")
public class Room extends BaseEntity{
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Message> messageList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_room",
                    joinColumns = @JoinColumn(name = "m-room_id"),
                    inverseJoinColumns = @JoinColumn(name = "m-user_id"))
    private Set<User> users = new HashSet<>();
}

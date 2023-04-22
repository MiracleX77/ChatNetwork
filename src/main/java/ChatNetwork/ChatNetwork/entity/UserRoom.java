package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_room")
public class UserRoom extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private  Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m-user_id")
    private  User user;
}

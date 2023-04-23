package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private Long idmes;
    @Column(nullable = false,length = 60)
    private String sender;

    @Column(nullable = false,length = 60)
    private String receiver;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date created;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name ="m_room_id",nullable = false)
//    private Room room;
}

package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m-message")
@Table(name = "message")
public class Message extends BaseEntity{
    @Column(nullable = false,length = 60)
    private String sender;

    @Column(nullable = false,length = 60)
    private String receiver;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="room_id",nullable = false)
    private Room room;
}

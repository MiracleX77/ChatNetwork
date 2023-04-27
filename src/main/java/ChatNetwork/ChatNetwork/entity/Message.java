package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "message_tb")
@Data
@AllArgsConstructor

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

    public Message(){
        created = new Date();
    }

}

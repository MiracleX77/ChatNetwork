package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name= "room_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room  {
    @Id
    @GeneratedValue
    private Long idroom;

    private Long talkerid;
    private String talkername;
}

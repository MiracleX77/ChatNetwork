package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name="m-user")
@AllArgsConstructor
@NoArgsConstructor
public class User   {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true,length = 60)
    private String email;

    @Column(nullable = false,length = 120)
    private String password;
    @Column(nullable = false,length = 120)
    private String name;

    @OneToMany(targetEntity = Room.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_rooms",referencedColumnName = "id")
    private List<Room> rooms;


}

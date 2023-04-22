package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m-user")
public class User extends BaseEntity {
    @Column(nullable = false,unique = true,length = 60)
    private String email;

    @Column(nullable = false,length = 120)
    private String password;
    @Column(nullable = false,length = 120)
    private String name;

    @ManyToMany(mappedBy = "users")
    private Set<Room> rooms = new HashSet<>();


}

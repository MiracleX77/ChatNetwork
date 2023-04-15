package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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


}

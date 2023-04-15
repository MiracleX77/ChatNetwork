package ChatNetwork.ChatNetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GenericGenerator(name="uuid2",strategy="uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false)
    private String id;
}

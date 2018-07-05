package pl.alburnus.auditing;

import lombok.Data;
import lombok.Value;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Audited
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Team(String name) {
        this.name = name;
    }
}

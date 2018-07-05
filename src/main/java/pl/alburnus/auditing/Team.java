package pl.alburnus.auditing;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date modifiedDate;

    public Team(String name) {
        this.name = name;
    }
}

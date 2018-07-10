package pl.alburnus.auditing.model;

import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import pl.alburnus.auditing.listeners.AuditingRevisionListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@RevisionEntity(AuditingRevisionListener.class)
@Table(name = "revinfo")
@Data
public class Revision implements Serializable {
    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "rev")
    private int revisionNumber;

    @RevisionTimestamp
    @Column(name = "revtstmp")
    private long revisionTimestamp;

    @Column(name = "createdBy")
    private String createdBy;
}

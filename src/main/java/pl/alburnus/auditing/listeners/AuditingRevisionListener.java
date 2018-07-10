package pl.alburnus.auditing.listeners;

import org.hibernate.envers.RevisionListener;
import pl.alburnus.auditing.model.Revision;

public class AuditingRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        Revision customRevision = (Revision) o;
        customRevision.setCreatedBy("User from context");
    }
}

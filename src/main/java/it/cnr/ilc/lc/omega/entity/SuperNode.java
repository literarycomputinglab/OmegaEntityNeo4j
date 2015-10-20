package it.cnr.ilc.lc.omega.entity;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author oakgen
 */
public abstract class SuperNode implements Serializable {

    public static enum Status {

        HISTORY, VALID, REMOVED
    }

    private URI uri; // FIXME: valutare se la proprietà URI è da considerarsi come proprietà di superclasse.
    private Long id;
    private Status status;
    private Date time;
    private SuperNode valid;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SuperNode getValid() {
        return valid;
    }

    public void setValid(SuperNode valid) {
        this.valid = valid;
    }
    
    

    // vedere se implementare il metodo clone

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
    
}

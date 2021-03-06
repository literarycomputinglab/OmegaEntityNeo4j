package it.cnr.ilc.lc.omega.entity;

import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author oakgen
 */
public abstract class SuperNode implements Serializable {

    public static enum Status {

        HISTORY, VALID, REMOVED
    }

    private String uri; // FIXME: valutare se la proprietà URI è da considerarsi come proprietà di superclasse.
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
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if (null != uri && !"".equals(uri)) {
            this.uri = uri;
        } else {
            throw new NullPointerException("NULL or Empty URI are not permitted");
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uri != null ? uri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final SuperNode other = (SuperNode) obj;

        if (!Objects.equals(this.uri, other.uri)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 *
 * @author oakgen
 */
@RelationshipEntity(type = "TEXTLOCUS")
public class TextLocus extends Locus<TextContent> {

    private Integer start;
    private Integer end;

    public Integer getStart() {
        if (start == null) {
            throw new NullPointerException("You are pointing the source not its content");
        }
        return start;
    }

    public void setStart(Integer start) {
        if (getPointTo().equals(PointTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to " + PointTo.SOURCE.name());
        }
        this.start = start;
    }

    public Integer getEnd() {
        if (end == null) {
            throw new NullPointerException("You are pointing the source not its content");
        }
        return end;
    }

    public void setEnd(Integer end) {
        if (getPointTo().equals(PointTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to" + PointTo.SOURCE.name());
        }
        this.end = end;
    }

    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

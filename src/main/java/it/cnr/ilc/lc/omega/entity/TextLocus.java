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
        return start;
    }

    public void setStart(Integer start) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to " + PointsTo.SOURCE.name());
        }
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to" + PointsTo.SOURCE.name());
        }
        this.end = end;
    }

    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

package it.cnr.ilc.lc.omega.entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 *
 * @author oakgen
 */
@RelationshipEntity(type = "TEXTLOCUS")
public class TextLocus extends Locus<TextContent> implements Cloneable{

    private Integer start;
    private Integer end;

    protected TextLocus(){
        
    }
    
    TextLocus(TextLocus locus) {
        this.start = locus.getStart();
        this.end = locus.getEnd();
    }
    
    
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
 
    // WARN controllare
    @Override
    public TextLocus clone(){
        TextLocus clone = null;
        try {
             clone = (TextLocus) super.clone();
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(TextLocus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clone;
    }
    
    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

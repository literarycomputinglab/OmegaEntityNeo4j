package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 *
 * @author angelo
 */

@RelationshipEntity(type = "IMAGELOCUS")
public final class ImageLocus extends Locus<ImageContent> implements Cloneable {

    private String WKTstr;

    protected ImageLocus() {

    }

    ImageLocus(ImageLocus locus) {
        this.WKTstr = locus.getWKTstr();
    }

    public String getWKTstr() {
        return WKTstr;
    }

    public void setWKTstr(String WKTstr) {
        if (getPointsTo().equals(Locus.PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to " + Locus.PointsTo.SOURCE.name());
        }
        this.WKTstr = WKTstr;
    }

    @Override
    public ImageLocus clone() {
        ImageLocus clone = null;
        try {
            clone = (ImageLocus) super.clone();

        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
        return clone;
    }

    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 *
 * @author angelo
 */

@RelationshipEntity(type = "IMAGELOCUS")
public class ImageLocus extends Locus<TextContent> implements Cloneable{

    private String WKTstr;
       
    protected ImageLocus(){
        
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
    
       
    // WARN controllare
    @Override
    public ImageLocus clone(){
        ImageLocus clone = null;
        try {
             clone = (ImageLocus) super.clone();
            
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
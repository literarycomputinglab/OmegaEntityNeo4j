/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author angelo
 */


@NodeEntity
public class ImageContent extends Content{
    //private String mimetype; 
    private Byte[] image;
   
//    @Override
//    public String getMimetype() {
//        return super.mimetype;
//    }

    @Override
    public void setMimetype(String mimetype) {
        super.mimetype = mimetype;
    }
    

    ImageContent() {
        super("image/*");
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    

}
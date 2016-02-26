package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author angelo
 */
@NodeEntity
public final class ImageContent extends Content{

    private Byte[] image;   

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
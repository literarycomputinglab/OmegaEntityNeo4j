package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public final class TextContent extends Content {

    private String text;

    TextContent() {
        super("text/plain");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    @Override
//    public String getMimetype() {
//        return mimetype;
//    }
//
//    @Override
//    public void setMimetype(String mimetype) {
//       this.mimetype = mimetype;
//       
//    }
    

}

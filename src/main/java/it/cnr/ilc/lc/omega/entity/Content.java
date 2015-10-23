package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public abstract class Content extends SuperNode {

    // FIXME: valutare se si vuole inserire il memetype all'interno del content supercalsse!
    // valutare se mettere un set per il contenuto
    protected String mimetype = "";

     Content(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) { //FIXME: neo4j vuole il metodo pubblico !!!
        this.mimetype = mimetype;
    }

    public static <T extends Content> T contentOf(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}

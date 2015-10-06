package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public abstract class Content extends SuperNode {

    public static <T extends Content> T contentOf(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}

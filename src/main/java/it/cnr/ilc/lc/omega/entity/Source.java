package it.cnr.ilc.lc.omega.entity;

import java.net.URI;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author simone
 * @param <T>
 */
@NodeEntity
public class Source<T extends Content> extends SuperNode {

    private T content;
    

    protected Source() {
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    
    public static <T extends Content> Source<T> sourceOf(Class<T> clazz, URI uri) {
        Source newSource = new Source<>();
        newSource.setUri(uri.toASCIIString());
        return  newSource;
    }
}

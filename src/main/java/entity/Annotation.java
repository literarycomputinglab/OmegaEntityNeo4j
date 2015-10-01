package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author oakgen
 * @param <T>
 * @param <E>
 */
@NodeEntity
public final class Annotation<T extends Content, E extends Annotation.Extension> extends Source<T> {

    private E extension;

    @Relationship(type = "TEXTLOCUS") //WARN FORZATO TEXTLOCUS: BUG APERTO SU NEO4J
    private List<Locus<T>> loci;

    @Relationship(type = "RELATION")
    private List<Relation> relations;

    private Annotation() {
    }

    public Iterator<Locus<T>> getLoci() {
        return loci.iterator();
    }

    private void setLoci(List<Locus<T>> loci) {
        this.loci = loci;
    }

    public void addLocus(Locus<T> locus) {
        if (loci == null) {
            setLoci(new ArrayList<Locus<T>>());
        }
        loci.add(locus);
    }

    public boolean removeLocus(Locus<T> locus) {
        if (loci == null) {
            return false;
        }
        return loci.remove(locus);
    }

    public E getExtension() {
        return extension;
    }

    private void setExtension(E extension) {
        this.extension = extension;
    }

    private void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public Iterator<Relation> getRelations() {
        return relations.iterator();
    }

    public void addRelation(Relation relation) {
        if (relations == null) {
            setRelations(new ArrayList<Relation>());
        }
        relations.add(relation);
    }

    public boolean removeRelation(Relation relation) {
        if (relations == null) {
            return false;
        }
        return relations.remove(relation);
    }

    public static abstract class Extension extends SuperNode {

        protected Extension() {
        }

        <T extends Extension> T build(Builder<T> builder) {
            return builder.build((T) this);
        }
    }

    private static final Map<String, Class<? extends Annotation.Extension>> LOOKUP_TABLE = new HashMap<>();

    public static void register(String type, Class<? extends Annotation.Extension> clazz) {
        LOOKUP_TABLE.put(type, clazz);
    }

    public static <T extends Content, E extends Annotation.Extension> Annotation<T, E> newAnnotation(String type, Builder<E> builder) {

        try {
            Annotation<T, E> annotation = new Annotation<>();
            Class<?> c = LOOKUP_TABLE.get(type);
            if (null == c) {
                throw new NullPointerException("No implementation found for type " + type);
            }
            E extension = (E) c.newInstance();
            annotation.setExtension(extension.build(builder));
            
            return annotation;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }

}

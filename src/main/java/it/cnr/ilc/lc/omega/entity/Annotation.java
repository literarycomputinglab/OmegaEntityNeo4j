package it.cnr.ilc.lc.omega.entity;

import it.cnr.ilc.lc.omega.exception.InvalidURIException;
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
public final class Annotation<T extends Content, E extends Annotation.Type> extends Source<T> {

    private E type;

    @Relationship(type = "LOCUS") //WARN FORZATO LOCUS: BUG APERTO SU NEO4J
    private List<Locus> loci = new ArrayList<>();

    @Relationship(type = "TEXTLOCUS") //WARN FORZATO TEXTLOCUS: BUG APERTO SU NEO4J
    private List<TextLocus> textloci = new ArrayList<>();

    @Relationship(type = "IMAGELOCUS") //WARN FORZATO IMAGELOCUS: BUG APERTO SU NEO4J
    private List<ImageLocus> imageloci = new ArrayList<>();

    @Relationship(type = "RELATION")
    private List<Relation> relations = new ArrayList<>();

    private Annotation() {
    }

    public Iterable<TextLocus> getTextLoci() {
        return textloci;
    }

    public Iterable<ImageLocus> getImageLoci() {
        return imageloci;
    }

    /**
     * WARNING: the locus has to be linked to source and target nodes
     *
     * @param <V>
     * @param locus
     *
     */
    public <V extends Content> void addLocus(Locus<V> locus) {
        locus.setAnnotation(this);
        if (locus instanceof TextLocus) {
            textloci.add(((TextLocus) locus).clone());
        }
        if (locus instanceof ImageLocus) {
            imageloci.add(((ImageLocus) locus).clone());
        }
        loci.add(locus);
    }

    // WARN controllare
    public <V extends Content> boolean removeLocus(Locus<V> locus) {
        if (locus instanceof TextLocus) {
            textloci.remove((TextLocus) locus);
        } else if (locus instanceof ImageLocus) {
            imageloci.remove((ImageLocus) locus);
        }
        for (int i = 0; i < loci.size(); i++) {
             if (loci.get(i).getUri().equals(locus.getUri())) {
               return loci.remove(i) != null;
            }
        }
        return false;
    }

    public E getType() {
        return type;
    }

    private void setType(E type) {
        this.type = type;
    }

    public Iterator<Relation> getRelations() {
        return relations.iterator();
    }

    public void addRelation(Relation relation) {

        relations.add(relation);
    }

    public boolean removeRelation(Relation relation) {

        return relations.remove(relation);
    }

    public static abstract class Type extends SuperNode {

        protected Type() {
        }

        <E extends Type> E build(AnnotationBuilder<E> builder) {
            return builder.build((E) this);
        }
    }

    private static final Map<String, Class<? extends Annotation.Type>> LOOKUP_TABLE = new HashMap<>();

    public static void register(String type, Class<? extends Annotation.Type> clazz) {
        LOOKUP_TABLE.put(type, clazz);
    }

    public static <T extends Content, E extends Annotation.Type> Annotation<T, E>
            newAnnotation(String type, AnnotationBuilder<E> builder) throws InvalidURIException {

        try {
            Annotation<T, E> annotation = new Annotation<>();
            Class<?> c = LOOKUP_TABLE.get(type);
            if (null == c) {
                throw new NullPointerException("No implementation found for type " + type);
            }
            E extension = (E) c.newInstance();
            annotation.setType(extension.build(builder));

            annotation.setUri(builder.getURI().toASCIIString()); //puo' sollevare eccezione se URI e' nulla o vuota

            return annotation;

        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }

}

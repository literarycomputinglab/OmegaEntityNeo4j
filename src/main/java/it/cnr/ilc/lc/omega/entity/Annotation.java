package it.cnr.ilc.lc.omega.entity;

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
    
    @Relationship(type = "LOCUS") //WARN FORZATO LOCUS: BUG APERTO SU NEO4J
    //private List<Locus> loci;
    private Map<Source, Locus> loci = new HashMap<>();
    
    @Relationship(type = "TEXTLOCUS") //WARN FORZATO TEXTLOCUS: BUG APERTO SU NEO4J
    private List<TextLocus> textloci = new ArrayList<>();
     
    @Relationship(type = "IMAGELOCUS") //WARN FORZATO IMAGELOCUS: BUG APERTO SU NEO4J
    private List<ImageLocus> imageloci = new ArrayList<>();
     
     

    @Relationship(type = "RELATION")
    private List<Relation> relations = new ArrayList<>();

    private Annotation() {
    }

    public Iterator<Locus> getLoci() {
        return loci.values().iterator();
    }

    public Iterator<TextLocus> getTextLoci() {
        return textloci.iterator();
    }
    
   /**
    * WARNING: the locus has to be linked to source and target nodes 
    * @param locus
    * 
    */

    public void addLocus(Locus<T> locus) {
        loci.put(locus.getSource(), locus);
        if(locus instanceof TextLocus)
            textloci.add(((TextLocus)locus).clone());
        if(locus instanceof ImageLocus)
            imageloci.add(((ImageLocus)locus).clone());
    }

    // WARN controllare
    public boolean removeLocus(Locus<T> locus) {
        if(locus instanceof TextLocus){
            return textloci.remove((TextLocus)locus);
        }
        else if (locus instanceof ImageLocus)
           return imageloci.remove((ImageLocus)locus);
        else return loci.remove(locus.getSource(), locus); // Controllare se non esistono più TextLocus e ImgeLocus con stesso source
    }

    public E getExtension() {
        return extension;
    }

    private void setExtension(E extension) {
        this.extension = extension;
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

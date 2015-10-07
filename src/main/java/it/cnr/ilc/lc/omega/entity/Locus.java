package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oakgen
 * @param <T>
 */
@RelationshipEntity
public class Locus<T extends Content> extends SuperNode implements Cloneable{

    public enum PointsTo {

        SOURCE, CONTENT;
    }

    @StartNode
    private Annotation annotation;

    @EndNode
    private Source<T> source;

    private String pointsTo;

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Source<T> getSource() {
        return source;
    }

    public void setSource(Source<T> source) {
        this.source = source;
    }

    public String getPointsTo() {
        return pointsTo;
    }

    void setPointsTo(String pointsTo) {
        this.pointsTo = pointsTo;
    }

    //WARN controllare
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public static <T extends Locus> T locusOf(Class<T> clazz, PointsTo pointTo) {
        try {
            T locus = clazz.newInstance();
            locus.setPointsTo(pointTo.name());
            return locus;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}

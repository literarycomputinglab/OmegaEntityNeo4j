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
public abstract class Locus<T extends Content> extends SuperNode {

    public enum PointTo {

        SOURCE, CONTENT;
    }

    @StartNode
    private Annotation annotation;

    @EndNode
    private Source<T> source;

    private String pointTo;

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

    public String getPointTo() {
        return pointTo;
    }

    void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    public static <T extends Locus> T locusOf(Class<T> clazz, PointTo pointTo) {
        try {
            T locus = clazz.newInstance();
            locus.setPointTo(pointTo.name());
            return locus;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}

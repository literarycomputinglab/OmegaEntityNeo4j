package it.cnr.ilc.lc.omega.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oakgen
 */
@RelationshipEntity
public final class Relation extends SuperNode {

    final private String type;

    @StartNode
    private Annotation sourceAnnotation;

    @EndNode
    private Annotation targetAnnotation;

    private Relation(Enum type) {
        this.type = type.name();
    }

    public Annotation getTargetAnnotation() {
        return targetAnnotation;
    }

    public void setTargetAnnotation(Annotation targetAnnotation) {
        this.targetAnnotation = targetAnnotation;
    }

    public Annotation getSourceAnnotation() {
        return sourceAnnotation;
    }

    public void setSourceAnnotation(Annotation sourceAnnotation) {
        this.sourceAnnotation = sourceAnnotation;
    }

    public String getType() {
        return type;
    }

    public static Relation newInstance(Enum type) {
        Relation r = new Relation(type);
        return r;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity;

import java.net.URI;

/**
 *
 * @author simone
 * @param <T>
 */
public abstract class AbstractAnnotationBuilder<T extends Annotation.Type> implements AnnotationBuilder<T> {

    URI uri;

    @Override
    final public void setURI(URI uri) {
        this.uri = uri;
    }

    @Override
    final public URI getURI() {
        return uri;
    }

}

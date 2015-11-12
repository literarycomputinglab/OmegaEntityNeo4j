/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity;

import java.net.URI;

/**
 *
 * @author angelo
 * @param <T>
 */
public interface AnnotationBuilder<T extends Annotation.Type> {

    T build(T extension);

    void setURI(URI uri);
    
    URI getURI();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.escuelaing.arem.picospring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author joelc
 */
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
public @interface RequestMapping {
    public String value();
}

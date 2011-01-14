/**
 * 
 */
package it.cnr.contab.cmis.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author mspasiano
 *
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface CMISType {
	public String name();
	public String[] parentName() default "";
}

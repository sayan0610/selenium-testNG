package com.glue.project.annotations;


import com.glue.project.annotations.finders.AbstractCustomFindByBuilder;
import com.glue.project.annotations.finders.CustomHow;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactoryFinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Annotation used in our page object models to define how web elements are found.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@PageFactoryFinder(FindBy.FindByBuilder.class)
public @interface FindBy {
    CustomHow how() default CustomHow.UNSET;

    String using() default "";

    String id() default "";

    String name() default "";

    String className() default "";

    String css() default "";

    String tagName() default "";

    String linkText() default "";

    String partialLinkText() default "";

    String xpath() default "";

    String reactjs() default "";

    String href() default "";

    String datatest() default "";

  /**
   * Inner class to build By-Strategies
   */

  public static class FindByBuilder extends AbstractCustomFindByBuilder {
      /**
       * Building By-Strategy based on the inputs of annotation.
       *
       * @param  annotation the input from inside of the annotation
       * @param  field      the field where the annotation is placed
       * @return the By Strategy
       */
      public By buildIt(Object annotation, Field field){
          FindBy findBy = (FindBy) annotation;

          By howToFind = buildByFromShortFindBy(findBy);
          if(howToFind == null) {
              howToFind = buildByFromLongFindBy(findBy);
          }

          return howToFind;
      }
  }



}

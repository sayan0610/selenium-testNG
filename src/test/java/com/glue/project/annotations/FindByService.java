package com.glue.project.annotations;

import net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationService;
import org.mockito.internal.matchers.Find;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class FindByService implements CustomFindByAnnotationService {


    @Override
    public boolean isAnnotatedByCustomFindByAnnotation(Field field) {
        return field.getDeclaredAnnotationsByType(FindBy.class).length == 1;
    }

    @Override
    public By buildByFromCustomFindByAnnotation(Field field) {
        Object annotation = field.getDeclaredAnnotationsByType(FindBy.class)[0];

        FindBy.FindByBuilder builder = new FindBy.FindByBuilder();
        return builder.buildIt(annotation,field);
    }
}

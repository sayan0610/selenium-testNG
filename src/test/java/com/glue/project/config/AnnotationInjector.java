package com.glue.project.config;

import com.glue.project.annotations.FindByService;
import net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationProviderService;
import net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationService;
import net.serenitybdd.core.di.WebDriverInjectors;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AnnotationInjector {

    @PostConstruct
    public void injectCustomFindByAnnotation(){
        FindByService findByService = new FindByService();
        WebDriverInjectors.getInjector().getInstance(CustomFindByAnnotationProviderService.class).
                getCustomFindByAnnotationServices().add(findByService);
    }
}

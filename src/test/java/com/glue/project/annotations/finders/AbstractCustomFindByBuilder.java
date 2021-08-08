package com.glue.project.annotations.finders;

import com.glue.project.annotations.FindBy;
import com.glue.project.annotations.finders.custom.ByDataTest;
import com.glue.project.annotations.finders.custom.ByHref;
import com.glue.project.annotations.finders.custom.ByReactJS;
import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;

import static org.seleniumhq.jetty9.util.StringUtil.isNotBlank;

/**
 * Custom FindByBuilder to overwrite the logic for building By's to add own extensions
 */

public abstract class AbstractCustomFindByBuilder extends AbstractFindByBuilder {

    /**
     * Responsible method for FindBy annotation in the form of @FindBy(name="branch")
     *
     * @param findBy is taken of the annotation class
     * @return the By to define how to find the web element
     */

    protected By buildByFromShortFindBy(FindBy findBy){

        //adding dataTest selector
        if(isNotBlank(findBy.datatest())){
            return ByDataTest.dataTest(findBy.datatest());
        }

        //adding href selector
        if(isNotBlank(findBy.href())){
            return ByHref.href(findBy.href());
        }

        //adding reactJS selector
        if(isNotBlank(findBy.reactjs())){
            return ByReactJS.reactJS(findBy.reactjs());
        }


        if(isNotBlank(findBy.className())){
            return By.className(findBy.className());
        }

        if(isNotBlank(findBy.css())){
            return By.cssSelector(findBy.css());
        }

        if(isNotBlank(findBy.id())){
            return By.id(findBy.id());
        }

        if(isNotBlank(findBy.linkText())){
            return By.linkText(findBy.linkText());
        }

        if(isNotBlank(findBy.name())){
            return By.name(findBy.name());
        }

        if(isNotBlank(findBy.partialLinkText())){
            return By.partialLinkText(findBy.partialLinkText());
        }

        if(isNotBlank(findBy.tagName())){
            return By.tagName(findBy.tagName());
        }

        if(isNotBlank(findBy.xpath())){
            return By.xpath(findBy.xpath());
        }

        return null;
    }

    /**
     * See method buildShortFindBy.
     * This Method is used for annotations in the form of: @FindBy(hoe =CustomHow.Name, using="loginType")
     *
     * @param findBy is taken of the annotation class
     * @return the By to define how to find the web element
     */

    protected By buildByFromLongFindBy(FindBy findBy){
        return findBy.how().buildBy(findBy.using());
    }
}

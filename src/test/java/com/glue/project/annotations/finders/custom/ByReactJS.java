package com.glue.project.annotations.finders.custom;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByCssSelector;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public class ByReactJS extends By implements Serializable {

    private final String ReactJS;

    public ByReactJS(String dataTest){ this.ReactJS=dataTest; }

    /**
     * Override of how to find all web elements, used to define rule for finding WebElements by ReactJS
     *
     * @param context A context used to search for
     * @return the lisdt of found web elements
     */

    @Override
    public List<WebElement> findElements(SearchContext context) {
        String cssReactJS = "[class^='" + ReactJS + "_']";
        List<WebElement> webElements = ((FindsByCssSelector) context).findElementsByCssSelector(cssReactJS);
        if(webElements.isEmpty()){
            throw new NoSuchElementException("Cannot locate an element using " + toString());
        }
        return webElements;
    }

    /**
     * Override of how to find all web elements, used to define rule for finding WebElements by ReactJS
     *
     * @param context A context used to search for
     * @return the list of found web elements
     */

    public WebElement findElement(SearchContext context) {
        List<WebElement> webElements = findElements(context);
        if(webElements.size() > 1){
            System.err.println("ATTENTION: More than one element found for identified" + ReactJS);
        }
        return webElements.get(0);
    }

    public static By reactJS(final String ReactJS){
        if(ReactJS == null)
            throw new IllegalArgumentException("Cannot find the elements when the ReactJS expression is null");
        return new ByReactJS(ReactJS);
    }
}

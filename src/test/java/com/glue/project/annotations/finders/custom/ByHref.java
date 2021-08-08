package com.glue.project.annotations.finders.custom;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByCssSelector;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public class ByHref extends By implements Serializable {

    private final String href;

    public ByHref(String dataTest){ this.href=dataTest; }

    /**
     * Override of how to find all web elements, used to define rule for finding WebElements by href
     *
     * @param context A context used to search for
     * @return the lisdt of found web elements
     */

    @Override
    public List<WebElement> findElements(SearchContext context) {
        String cssHref = "a[href='" + href + "']";
        List<WebElement> webElements = ((FindsByCssSelector) context).findElementsByCssSelector(cssHref);
        if(webElements.isEmpty()){
            throw new NoSuchElementException("Cannot locate an element using " + toString());
        }
        return webElements;
    }

    /**
     * Override of how to find all web elements, used to define rule for finding WebElements by href
     *
     * @param context A context used to search for
     * @return the list of found web elements
     */

    public WebElement findElement(SearchContext context) {
        List<WebElement> webElements = findElements(context);
        if(webElements.size() > 1){
            System.err.println("ATTENTION: More than one element found for identified" + href);
        }
        return webElements.get(0);
    }

    public static By href(final String href){
        if(href == null)
            throw new IllegalArgumentException("Cannot find the elements when the Href expression is null");
        return new ByHref(href);
    }
}

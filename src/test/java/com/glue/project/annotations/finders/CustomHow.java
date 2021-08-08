package com.glue.project.annotations.finders;

import com.glue.project.annotations.finders.custom.ByDataTest;
import com.glue.project.annotations.finders.custom.ByHref;
import com.glue.project.annotations.finders.custom.ByReactJS;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;

/**
 * Is used for long format of custom FindBy annotation
 */
public enum CustomHow {
    CLASS_NAME{
        @Override
        public By buildBy(String value){return By.className(value);}
    },
    CSS{
        @Override
        public By buildBy(String value){return By.cssSelector(value);}
    },
    ID{
        @Override
        public By buildBy(String value){return By.id(value);}
    },
    ID_OR_NAME{
        @Override
        public By buildBy(String value){return new ByIdOrName(value);}
    },
    LINK_TEXT{
        @Override
        public By buildBy(String value){return By.linkText(value);}
    },
    NAME{
        @Override
        public By buildBy(String value){return By.name(value);}
    },
    PARTIAL_LINK_TEST{
        @Override
        public By buildBy(String value){return By.partialLinkText(value);}
    },
    TAG_NAME{
        @Override
        public By buildBy(String value){return By.tagName(value);}
    },
    XPATH{
        @Override
        public By buildBy(String value){return By.xpath(value);}
    },
    REACTJS{
        @Override
        public By buildBy(String value){return ByReactJS.cssSelector(value);}
    },
    HREF{
        @Override
        public By buildBy(String value){return ByHref.cssSelector(value);}
    },
    DATATEST{
        @Override
        public By buildBy(String value){return ByDataTest.cssSelector(value);}
    },
    UNSET{
        @Override
        public By buildBy(String value){return ID.buildBy(value);}
    };

    public abstract By buildBy(String value);
}
